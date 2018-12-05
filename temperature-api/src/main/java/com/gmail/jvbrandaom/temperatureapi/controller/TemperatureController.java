package com.gmail.jvbrandaom.temperatureapi.controller;

import com.gmail.jvbrandaom.temperatureapi.exception.TemperatureException;
import com.gmail.jvbrandaom.temperatureapi.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemperatureController {

    @Autowired
    private TemperatureService temperatureService;

    @GetMapping(path = "/temperature/{city}")
    public Double getTemperatureFromCity(@PathVariable String city) throws TemperatureException {
        return temperatureService.getTemperatureFromCity(city);
    }

    @GetMapping(path = "/temperature/latitude/{latitude}/longitude/{longitude}")
    public Double getTemperatureFromCoordinates(@PathVariable Double latitude, @PathVariable Double longitude) throws TemperatureException {
        return temperatureService.getTemperatureFromCoordinates(latitude, longitude);
    }
}
