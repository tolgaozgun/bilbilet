package edu.bilkent.bilbilet.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.bilkent.bilbilet.enums.UserType;
import edu.bilkent.bilbilet.model.User;

@Qualifier("example_repo")
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

    static User[] arr = {new User(0, "elo", "elogus", "elo@elo.com", "555222", "123456", UserType.ADMIN)};
    static List<User> users = Arrays.asList(arr);
    
    public User findUserByMail(String mail) {
        String sql = "SELECT * FROM \"User\" WHERE email = ?";

        try {
            return jdbcTemplate.queryForObject(sql, userRowMapper, mail);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public User save(User user) { //check if exist????????????
        String sql = "INSERT INTO \"User\" (name, surname, email, telephone, password, user_type) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        
        jdbcTemplate.update(sql, user.getName(), user.getSurname(), user.getEmail(),
                            user.getTelephone(), user.getPassword(), user.getUserType().toString());
        
        return findUserByMail(user.getEmail());
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
