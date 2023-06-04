package edu.bilkent.bilbilet.controller;

import edu.bilkent.bilbilet.model.*;
import edu.bilkent.bilbilet.repository.rowmapper.datamodel.CompanyCarRM;
import edu.bilkent.bilbilet.request.CompanyRegister;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.bilkent.bilbilet.exception.ExceptionLogger;
import edu.bilkent.bilbilet.request.TravelerRegister;
import edu.bilkent.bilbilet.request.UserLogin;
import edu.bilkent.bilbilet.response.RSystemReport;
import edu.bilkent.bilbilet.response.RUserToken;
import edu.bilkent.bilbilet.response.Response;
import edu.bilkent.bilbilet.service.AccountService;
import edu.bilkent.bilbilet.service.SystemReportService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/sysReport")
public class SystemReportController {

    private final SystemReportService systemReportService;

    @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
    @GetMapping()
    public ResponseEntity<Object> getCompany() {
        try {
            RSystemReport companyInfo = systemReportService.generateSysReport();
            return Response.create("ok", HttpStatus.OK, companyInfo);
        } catch (Exception e) {
            return Response.create(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}