package com.gmail.jvbrandaom.songrecommender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@SpringBootApplication
@EnableFeignClients
@EnableOAuth2Client
public class SongRecommenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SongRecommenderApplication.class, args);
	}
}
