package edu.bilkent.bilbilet.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

@Component
public class DatabaseInitializer {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // @PostConstruct
    public void initializeDatabase() throws IOException {
        ClassPathResource resource = new ClassPathResource("sql/init.sql");
        String sqlScript = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        try {
            ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(), resource);
        } catch (ScriptException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("db is initialized");

        // You can also execute the SQL script directly using jdbcTemplate:
        // jdbcTemplate.execute(sqlScript);
    }
}