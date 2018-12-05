package com.gmail.jvbrandaom.temperatureapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TemperatureApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TemperatureApiApplication.class, args);
	}
}
