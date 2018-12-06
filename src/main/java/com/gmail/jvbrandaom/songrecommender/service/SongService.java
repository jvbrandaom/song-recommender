package com.gmail.jvbrandaom.songrecommender.service;

import com.gmail.jvbrandaom.songrecommender.domain.Token;
import com.gmail.jvbrandaom.songrecommender.restclient.SongClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class SongService {

    @Autowired
    private SongClient songClient;
    @Value("${spotify.api.client.id}")
    private String clientId;
    @Value("${spotify.api.client.secret}")
    private String clientSecret;

    public Token getToken() {
        Map<String, String> body = new HashMap<>();
        body.put("grant_type", "client_credentials");
        return songClient.getToken(body, getEncodedAuthorizationString());
    }

    private String getEncodedAuthorizationString() {
        String authorizationString = String.format("%s:%s", clientId, clientSecret);
        String encodedAuthorization = new String(Base64.getEncoder().encode(authorizationString.getBytes()));
        return String.format("Basic %s", encodedAuthorization);
    }
}
