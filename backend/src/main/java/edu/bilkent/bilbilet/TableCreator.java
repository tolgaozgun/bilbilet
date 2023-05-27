package edu.bilkent.bilbilet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class TableCreator {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createTable() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS User (  user_id INT NOT NULL AUTO_INCREMENT,  name VARCHAR(50) NOT NULL,  surname VARCHAR(50) NOT NULL,  email VARCHAR(320) UNIQUE NOT NULL,  telephone VARCHAR(50) UNIQUE NOT NULL,  password VARCHAR(255) NOT NULL,  user_type VARCHAR(255) NOT NULL,  PRIMARY KEY (user_id) ); ";
        jdbcTemplate.execute(createTableQuery);
        System.out.println("Table created successfully.");
    }
}