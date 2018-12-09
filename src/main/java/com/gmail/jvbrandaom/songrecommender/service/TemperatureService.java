package com.gmail.jvbrandaom.songrecommender.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.jvbrandaom.songrecommender.restclient.WeatherClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TemperatureService {
    @Value("${open-weather.api.key}")
    private String apiKey;

    @Autowired
    private WeatherClient openWeatherClient;

    private ObjectMapper objectMapper = new ObjectMapper();

    public Double getTemperatureFromCity(String city) {
        return openWeatherClient.getWeather(city, apiKey, "metric");
    }

    public Double getTemperatureFromCoordinates(Double latitude, Double longitude) {
        return openWeatherClient.getWeather(latitude, longitude, apiKey, "metric");
    }
}
