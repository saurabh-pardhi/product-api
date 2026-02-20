//package com.zest.productapi;
//
//import jakarta.annotation.PostConstruct;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.TestPropertySource;
//
//import java.util.TimeZone;
//
//@SpringBootTest
//@TestPropertySource(properties = {
//		"spring.jpa.properties.hibernate.jdbc.time_zone=UTC"
//})
//class ProductApiApplicationTests {
//
//	@Test
//	void contextLoads() {
//
//	}
//	@PostConstruct
//	public void init() {
//		// Set default timezone to UTC
//		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
//	}
//
//}
