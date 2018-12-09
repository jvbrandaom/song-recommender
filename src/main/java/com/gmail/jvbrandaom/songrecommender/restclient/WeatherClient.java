package com.gmail.jvbrandaom.songrecommender.restclient;

import com.gmail.jvbrandaom.songrecommender.config.WeatherClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "weather", url = "${open-weather.api.url}", fallback = WeatherClient.WeatherClientFallback.class, configuration = WeatherClientConfiguration.class)
public interface WeatherClient {

    @GetMapping
    Double getWeather(@RequestParam(value = "q") String city, @RequestParam(value="APPID") String apiKey, @RequestParam(value = "units", defaultValue = "metric") String unit);

    @GetMapping
    Double getWeather(@RequestParam(value = "lat") Double latitude, @RequestParam(value = "lon") Double longitude, @RequestParam(value="APPID") String apiKey, @RequestParam(value = "units", defaultValue = "metric") String metric);

    @Component
    class WeatherClientFallback implements WeatherClient {
        @Override
        public Double getWeather(String city, String apiKey, String unit) {
            return 24.9999;
        }

        @Override
        public Double getWeather(Double latitude, Double longitude, String apiKey, String metric) {
            return 24.9999;
        }
    }
}
