package edu.bilkent.bilbilet.service;

import edu.bilkent.bilbilet.model.Company;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CompanyService {

    private final Map<Integer, Company> companies = new HashMap<>();
    private int nextCompanyId = 1;

    public List<Company> getAllCompanies() {
        return new ArrayList<>(companies.values());
    }

    public Company getCompanyById(int companyId) {
        return companies.get(companyId);
    }

    public Company createCompany(Company company) {
        company.setCompany_id(nextCompanyId++);
        companies.put(company.getCompany_id(), company);
        return company;
    }

    public Company updateCompany(int companyId, Company updatedCompany) {
        if (companies.containsKey(companyId)) {
            updatedCompany.setCompany_id(companyId);
            companies.put(companyId, updatedCompany);
            return updatedCompany;
        }
        return null;
    }

    public boolean deleteCompany(int companyId) {
        if (companies.containsKey(companyId)) {
            companies.remove(companyId);
            return true;
        }
        return false;
    }
}
