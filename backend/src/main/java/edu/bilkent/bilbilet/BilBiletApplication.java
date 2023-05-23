package edu.bilkent.bilbilet;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

import edu.bilkent.bilbilet.database.DatabaseInitializer;
import jakarta.annotation.PostConstruct;

// @Slf4j
@SpringBootApplication
public class BilBiletApplication {
	// @Autowired
	// DatabaseInitializer databaseInitializer;

	public static void main(String[] args) {
		SpringApplication.run(BilBiletApplication.class, args);
		System.out.println("hola como estas???");
	}

	// @PostConstruct
    // public void initializeDatabase() throws IOException {
    //     databaseInitializer.initializeDatabase();
    // }

}
