package edu.bilkent.bilbilet.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.nio.charset.StandardCharsets;

@Configuration
@Component
public class DatabaseInitializer {

    @Autowired
    private JdbcTemplate jdbcTemplate;    

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initializeTriggers() {
        ResourceDatabasePopulator triggersPopulator = 
            new ResourceDatabasePopulator(false, false, StandardCharsets.UTF_8.toString(), new ClassPathResource("sql/triggers.sql"));
        triggersPopulator.setSeparator("//");
        triggersPopulator.execute(dataSource);

        System.out.println("hola triger");
    }

    @PostConstruct
    public void initializeDatabase() throws IOException {
        ClassPathResource resource = new ClassPathResource("sql/init.sql");
        ClassPathResource data = new ClassPathResource("sql/data.sql");
        // ClassPathResource trigger = new ClassPathResource("sql/triggers.sql");

        try {
            ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(), resource);
            ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(), data);
            // ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(), trigger);
        } catch (ScriptException | SQLException e) {
            e.printStackTrace();
        }

        System.out.println("db is initialized");
    }
}