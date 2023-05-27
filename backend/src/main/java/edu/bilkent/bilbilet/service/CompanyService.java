package edu.bilkent.bilbilet.service;

import edu.bilkent.bilbilet.model.Company;
import edu.bilkent.bilbilet.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.getAllCompanies();
    }

    public Company getCompanyById(int companyId) {
        return companyRepository.getCompanyById(companyId);
    }

    public Company createCompany(Company company) {
        return companyRepository.createCompany(company);
    }

    public Company updateCompany(int companyId, Company company) {
        return companyRepository.updateCompany(companyId, company);
    }

    public boolean deleteCompany(int companyId) {
        return companyRepository.deleteCompany(companyId);
    }
}
