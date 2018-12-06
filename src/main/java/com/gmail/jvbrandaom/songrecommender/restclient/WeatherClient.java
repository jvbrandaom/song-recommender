package com.gmail.jvbrandaom.songrecommender.restclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "weather", url = "${open-weather.api.url}")
public interface WeatherClient {

    @GetMapping
    String getWeather(@RequestParam(value = "q") String city, @RequestParam(value="APPID") String apiKey, @RequestParam(value = "units", defaultValue = "metric") String unit);

    @GetMapping
    String getWeather(@RequestParam(value = "lat") Double latitude, @RequestParam(value = "lon") Double longitude, @RequestParam(value="APPID") String apiKey, @RequestParam(value = "units", defaultValue = "metric") String metric);
}
