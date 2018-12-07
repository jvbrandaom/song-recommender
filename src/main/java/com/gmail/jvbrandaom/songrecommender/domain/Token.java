package com.gmail.jvbrandaom.songrecommender.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Token {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("expires_in")
    private Integer expiresIn;

    @Override
    public String toString() {
        return String.format("Bearer %s", accessToken);
    }
}
