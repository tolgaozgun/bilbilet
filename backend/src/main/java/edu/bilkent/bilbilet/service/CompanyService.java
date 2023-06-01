package edu.bilkent.bilbilet.service;

import edu.bilkent.bilbilet.model.Company;
import edu.bilkent.bilbilet.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public List<Company> getAllCompanies() throws Exception {
        try {
            Optional<List<Company>> companies = companyRepository.getAllCompanies();
            return companies.orElse(Collections.emptyList());
        } catch (Exception e) {
            System.out.println("Get All Companies exception");
            e.printStackTrace();
            throw e;
        }
    }

    public Company getCompanyById(int companyId) throws Exception {
        try {
            Optional<Company> company = companyRepository.getCompanyById(companyId);
            if (company.isEmpty()) {
                throw new Exception("Company does not exist");
            }
            return company.get();
        } catch (Exception e) {
            System.out.println("Get Company By ID Exception");
            e.printStackTrace();
            throw e;
        }
    }

    public Company createCompany(Company company) throws Exception {
        try {
            Optional<Company> registeredCompany = companyRepository.getCompanyById(company.getCompany_id());
            if(registeredCompany.isPresent()){
                throw new Exception("Company with ID " + company.getCompany_id() + " already exists!");
            }

            Optional<Company> createdCompany = companyRepository.createCompany(company);
            if (createdCompany.isEmpty()) {
                throw new Exception("Failed to create new company");
            }
            return createdCompany.get();
        } catch (Exception e) {
            System.out.println("Create Company Exception");
            e.printStackTrace();
            throw e;
        }
    }

    public Company updateCompany(int companyId, Company company) throws Exception {
        try {
            Optional<Company> registeredCompany = companyRepository.getCompanyById(company.getCompany_id());
            if (registeredCompany.isEmpty()) {
                throw new Exception("Company with ID " + company.getCompany_id() + " does not exist!");
            }
            Optional<Company> updatedCompany = companyRepository.updateCompany(companyId, company);
            if (updatedCompany.isEmpty()) {
                throw new Exception("Error while updating company");
            }

            return updatedCompany.get();
        } catch (Exception e) {
            System.out.println("Update company exception");
            e.printStackTrace();
            throw e;

        }
    }

    public boolean deleteCompany(int companyId) throws Exception {
        try {
            Optional<Company> registeredCompany = companyRepository.getCompanyById(companyId);
            if (registeredCompany.isEmpty()) {
                throw new Exception("Company with ID " + companyId + " does not exist!");
            }
            return companyRepository.deleteCompany(companyId);
        } catch (Exception e) {
            System.out.println("Delete company exception");
            e.printStackTrace();
            throw e;

        }
    }
}
