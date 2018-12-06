package com.gmail.jvbrandaom.songrecommender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SongRecommenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SongRecommenderApplication.class, args);
	}
}
