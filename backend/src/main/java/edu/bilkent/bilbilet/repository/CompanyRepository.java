package edu.bilkent.bilbilet.repository;

import edu.bilkent.bilbilet.enums.CompanyType;
import edu.bilkent.bilbilet.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CompanyRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Company> getAllCompanies() {
        String sql = "SELECT * FROM Company";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Company company = new Company();
            company.setCompany_id(rs.getInt("company_id"));
            company.setCompany_title(rs.getString("company_title"));
            company.setAddress(rs.getString("address"));
            company.setType(CompanyType.valueOf(rs.getString("type")));
            company.setContact_information(rs.getString("contact_information"));
            company.setBusiness_registration(rs.getString("business_registration"));
            company.setBalance(rs.getBigDecimal("balance"));
            company.setUser_id(rs.getInt("user_id"));
            return company;
        });
    }

    public Company getCompanyById(int companyId) {
        String sql = "SELECT * FROM Company WHERE company_id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Company company = new Company();
            company.setCompany_id(rs.getInt("company_id"));
            company.setCompany_title(rs.getString("company_title"));
            company.setAddress(rs.getString("address"));
            company.setType(CompanyType.valueOf(rs.getString("type")));
            company.setContact_information(rs.getString("contact_information"));
            company.setBusiness_registration(rs.getString("business_registration"));
            company.setBalance(rs.getBigDecimal("balance"));
            company.setUser_id(rs.getInt("user_id"));
            return company;
        }, companyId);
    }

    public Company createCompany(Company company) {
        String sql = "INSERT INTO Company (company_title, address, type, contact_information, business_registration, balance, user_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                company.getCompany_title(),
                company.getAddress(),
                company.getType(),
                company.getContact_information(),
                company.getBusiness_registration(),
                company.getBalance(),
                company.getUser_id());
        return company;
    }

    public Company updateCompany(int companyId, Company company) {
        String sql = "UPDATE Company SET company_title = ?, address = ?, type = ?, contact_information = ?, " +
                "business_registration = ?, balance = ?, user_id = ? WHERE company_id = ?";
        int affectedRows = jdbcTemplate.update(sql,
                company.getCompany_title(),
                company.getAddress(),
                company.getType(),
                company.getContact_information(),
                company.getBusiness_registration(),
                company.getBalance(),
                company.getUser_id(),
                companyId);
        if (affectedRows > 0) {
            company.setCompany_id(companyId);
            return company;
        }
        return null;
    }

    public boolean deleteCompany(int companyId) {
        String sql = "DELETE FROM Company WHERE company_id = ?";
        int affectedRows = jdbcTemplate.update(sql, companyId);
        return affectedRows > 0;
    }
}
