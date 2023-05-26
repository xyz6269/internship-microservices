package com.example.menuservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class MenuServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MenuServiceApplication.class, args);
	}

}


// token endpoint : http://localhost:8181/realms/food-delivery-microservices-realm/protocol/openid-connect/token

// client secret : 	dqqobNVEOxeFpmEylYBXA6opIptQ7gHE