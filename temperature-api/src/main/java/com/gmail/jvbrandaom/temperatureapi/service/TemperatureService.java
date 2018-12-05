package com.gmail.jvbrandaom.temperatureapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.jvbrandaom.temperatureapi.exception.TemperatureException;
import com.gmail.jvbrandaom.temperatureapi.restclient.WeatherClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TemperatureService {
    @Value("${open-weather.api.key}")
    private String apiKey;

    @Autowired
    private WeatherClient openWeatherClient;

    private ObjectMapper objectMapper = new ObjectMapper();

    public Double getTemperatureFromCity(String city) throws TemperatureException {
        String weather = openWeatherClient.getWeather(city, apiKey, "metric");
        try {
            return objectMapper.readTree(weather).get("main").get("temp").asDouble();
        } catch (IOException e) {
            throw new TemperatureException(String.format("Cannot fetch temperature for city %s", city));
        }
    }

    public Double getTemperatureFromCoordinates(Double latitude, Double longitude) throws TemperatureException {
        String weather = openWeatherClient.getWeather(latitude, longitude, apiKey, "metric");
        try {
            return objectMapper.readTree(weather).get("main").get("temp").asDouble();
        } catch (IOException e) {
            throw new TemperatureException(String.format("Cannot fetch temperature for coordinates latitude: %s, longitude: %s ", latitude, longitude));
        }
    }
}
