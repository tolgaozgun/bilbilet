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

    @PostConstruct
    public void initializeDatabase() throws IOException {
        ClassPathResource resource = new ClassPathResource("sql/init.sql");
        ClassPathResource data = new ClassPathResource("sql/data.sql");
        ClassPathResource trigger = new ClassPathResource("sql/triggers.sql");
        String sqlScript = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        try {
            ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(), resource);
            ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(), data);
            ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(), trigger);
        } catch (ScriptException | SQLException e) {
            e.printStackTrace();
        }

        System.out.println("db is initialized");
    }
}