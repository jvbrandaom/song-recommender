package com.gmail.jvbrandaom.songrecommender.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import feign.Logger;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.lang.reflect.Type;

public class WeatherClientConfiguration {
    @Bean
    public Decoder customDecoder() {
        return new WeatherDecoder();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    private class WeatherDecoder implements Decoder {
        @Override
        public Double decode(Response response, Type type) throws IOException, DecodeException, FeignException {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.body().asInputStream());
            return rootNode.get("main").get("temp").asDouble();
        }
    }
}
