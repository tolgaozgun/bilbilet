package edu.bilkent.bilbilet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

// @Slf4j
@SpringBootApplication
public class BilBiletApplication {

	public static void main(String[] args) {
		SpringApplication.run(BilBiletApplication.class, args);
	}

}
