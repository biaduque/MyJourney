package com.faculdade.MyJourney;

import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@KeycloakConfiguration
public class MyJourneyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyJourneyApplication.class, args);
	}

}
