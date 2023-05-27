package edu.bilkent.bilbilet.controller;

import edu.bilkent.bilbilet.exception.ExceptionLogger;
import edu.bilkent.bilbilet.model.Company;
import edu.bilkent.bilbilet.response.Response;
import edu.bilkent.bilbilet.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/companies")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllCompanies() {
        try{
            List<Company> companies = companyService.getAllCompanies();
            return Response.create("Gathered all companies", HttpStatus.OK, companies);
        }catch(Exception e){
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{companyId}")
    public ResponseEntity<Object> getCompanyById(@PathVariable int companyId) {
        try{
            Company company = companyService.getCompanyById(companyId);
            if (company != null) {
                return Response.create("Gathered company", HttpStatus.OK, company);
            }
            return Response.create("Could not find company", HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createCompany(@RequestBody @Valid Company company) {
        try{
            Company createdCompany = companyService.createCompany(company);
            return Response.create("Created company successfully", HttpStatus.CREATED, createdCompany);
        }catch(Exception e){
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<Object> updateCompany(@PathVariable int companyId, @RequestBody @Valid Company company) {
        try{
            Company updatedCompany = companyService.updateCompany(companyId, company);
            if (updatedCompany != null) {
                return Response.create("Updated company", HttpStatus.OK, company);
            }
            return Response.create("Could not find company", HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<Object> deleteCompany(@PathVariable int companyId) {
        try{
            if (companyService.deleteCompany(companyId)) {
                return Response.create("Deleted company", HttpStatus.OK);
            }
            return Response.create("Could not find company", HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return Response.create(ExceptionLogger.log(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

