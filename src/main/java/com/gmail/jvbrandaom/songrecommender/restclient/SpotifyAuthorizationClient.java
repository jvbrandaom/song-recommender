package com.gmail.jvbrandaom.songrecommender.restclient;

import com.gmail.jvbrandaom.songrecommender.domain.Token;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(value = "spotifyAuthorizationClient", url = "${spotify.accounts.url}")
public interface SpotifyAuthorizationClient {
    @PostMapping(value = "/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    Token getToken(@RequestBody Map<String, ?> formParams, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization);
}
