package com.zest.productapi;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.TimeZone;

@SpringBootApplication
public class ProductApiApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
		String raw = "admin123";
		String hash = "$2a$12$3NSX1QDGp.YN.EuBgfKCn.5k87pw64NHemkGc4iq76/BI.1X.7bBO";

		System.out.println(encoder.matches(raw, hash)); //
		SpringApplication.run(ProductApiApplication.class, args);
	}
	@PostConstruct
	public void init() {
		// Set default timezone to UTC
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

}
