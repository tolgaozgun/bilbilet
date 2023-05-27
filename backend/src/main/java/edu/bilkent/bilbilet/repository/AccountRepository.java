package edu.bilkent.bilbilet.repository;

import java.math.BigDecimal;
import java.util.Optional;

import edu.bilkent.bilbilet.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import edu.bilkent.bilbilet.enums.UserType;
import edu.bilkent.bilbilet.model.Traveler;
import edu.bilkent.bilbilet.model.User;

@Qualifier("account_repo")
@Repository
public class AccountRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setName(rs.getString("name"));
        user.setSurname(rs.getString("surname"));
        user.setEmail(rs.getString("email"));
        user.setTelephone(rs.getString("telephone"));
        user.setPassword(rs.getString("password"));
        user.setUserType(UserType.valueOf(rs.getString("user_type")));
        return user;
    };
    
    private RowMapper<Traveler> travelerRowMapper = (rs, rowNum) -> {
        Traveler traveler = new Traveler();
        traveler.setUser_id(rs.getInt("user_id"));
        traveler.setNationality(rs.getString("nationality"));
        traveler.setPassport_number(rs.getString("passport_number"));
        traveler.setBalance(rs.getBigDecimal("balance"));
        traveler.setTCK(rs.getString("TCK"));
        return traveler;
    };

    private RowMapper<Company> companyRowMapper = (rs, rowNum) -> {
        Company company = new Company();
        company.setCompany_id(rs.getInt("company_id"));
        company.setCompany_title(rs.getString("company_title"));
        company.setAddress(rs.getString("address"));
        company.setContact_information(rs.getString("contact_information"));
        company.setBusiness_registration(rs.getString("business_registration"));
        company.setBalance(rs.getBigDecimal("balance"));
//        company.setType(rs.get);
        return company;
    };
    
    public User findUserByMail(String mail) {
        String sql = "SELECT * FROM User WHERE email = ?";

        try {
            return jdbcTemplate.queryForObject(sql, userRowMapper, mail);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Optional<Traveler> findTravelerByUserId(int id) {
        String sql = "SELECT * FROM Traveler WHERE user_id = ?";

        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, travelerRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public Optional<Company> findCompanyByUserId(int id) {
        String sql = "SELECT * FROM Company WHERE user_id = ?";

        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, companyRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public User save(User user) { //check if exist????????????
        String sql = "INSERT INTO User (name, surname, email, telephone, password, user_type) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        
        jdbcTemplate.update(
            sql,
            user.getName(),
            user.getSurname(),
            user.getEmail(),
            user.getTelephone(),
            user.getPassword(),
            user.getUserType().toString()
        );
        
        User new_user = findUserByMail(user.getEmail());
        new_user.setPassword(null);
        return new_user;
    }

    public Optional<Traveler> save(Traveler traveler) { //check if exist????????????
        String sql = "INSERT INTO Traveler (user_id, nationality, passport_number, balance, TCK) " +
                     "VALUES (?, ?, ?, ?, ?)";
        
        jdbcTemplate.update(
            sql,
            traveler.getUser_id(),
            traveler.getNationality(),
            traveler.getPassport_number(),
            new BigDecimal(0),
            traveler.getTCK()
        );

        return findTravelerByUserId(traveler.getUser_id());
    }


    public Optional<Company> save(Company company) { //check if exist????????????
        String sql = "INSERT INTO Company (company_id, company_title, address, type, contact_information, " +
                "business_registration, balance, user_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(
                sql,
                company.getCompany_id(),
                company.getCompany_title(),
                company.getAddress(),
                company.getType(),
                company.getContact_information(),
                company.getBusiness_registration(),
                company.getBalance(),
                company.getUser_id()
        );

        return findCompanyByUserId(company.getUser_id());
    }

    // public User save(User user) {
    //     users.add(user);
    //     return user;
    // }

    public boolean existsByEmail(String mail) {
        User user = findUserByMail(mail);

        return user != null;
    }
}
