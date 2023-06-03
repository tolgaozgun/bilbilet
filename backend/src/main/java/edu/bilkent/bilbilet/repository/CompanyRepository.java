package edu.bilkent.bilbilet.repository;

import edu.bilkent.bilbilet.enums.CompanyType;
import edu.bilkent.bilbilet.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Qualifier("company_repo")
@Repository
public class CompanyRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Company> companyRowMapper = (rs, rowNum) -> {
        Company company = new Company();
        company.setCompany_id(rs.getInt("company_id"));
        company.setCompany_title(rs.getString("company_title"));
        company.setAddress(rs.getString("address"));
        company.setContact_information(rs.getString("contact_information"));
        company.setBusiness_registration(rs.getString("business_registration"));
        company.setBalance(rs.getBigDecimal("balance"));
        company.setType(CompanyType.valueOf(rs.getString("type")));
        return company;
    };

    public Optional<List<Company>> getAllCompanies() {
        String sql = "SELECT * FROM Company";
        try {
            return Optional.of(jdbcTemplate.query(sql, companyRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public Optional<Company> getCompanyById(int companyId) {
        String sql = "SELECT * FROM Company WHERE company_id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, companyRowMapper, companyId));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public Optional<Company> getCompanyByTitle(String title) {
        String sql = "SELECT * FROM Company WHERE company_title = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, companyRowMapper, title));
        }
        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public Optional<Company> getCompanyByName(String name) {
        String sql = "SELECT * FROM Company WHERE company_title = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, companyRowMapper, name));
        }
        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public Optional<Company> createCompany(Company company) {
        String sql = "INSERT INTO Company (company_title, address, type, contact_information, business_registration, balance, user_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                company.getCompany_title(),
                company.getAddress(),
                company.getType().toString(),
                company.getContact_information(),
                company.getBusiness_registration(),
                company.getBalance(),
                company.getUser_id()
        );
        return getCompanyById(company.getCompany_id());
    }

    public Optional<Company> updateCompany(int companyId, Company company) {
        String sql = "UPDATE Company SET company_title = ?, address = ?, type = ?, contact_information = ?, " +
                "business_registration = ?, balance = ?, user_id = ? WHERE company_id = ?";

        int affectedRows = jdbcTemplate.update(sql,
                company.getCompany_title(),
                company.getAddress(),
                company.getType().toString(),
                company.getContact_information(),
                company.getBusiness_registration(),
                company.getBalance(),
                company.getUser_id(),
                companyId
        );
        if (affectedRows > 0) {
            company.setCompany_id(companyId);
            return Optional.of(company);
        }
        return Optional.empty();
    }

    public boolean deleteCompany(int companyId) {
        String sql = "DELETE FROM Company WHERE company_id = ?";
        int affectedRows = jdbcTemplate.update(sql, companyId);
        return affectedRows > 0;
    }

}
