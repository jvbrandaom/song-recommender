package com.gmail.jvbrandaom.songrecommender.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rule implements Serializable {
    private Double minTemperature = -Double.MAX_VALUE;
    private Double maxTemperature = Double.MAX_VALUE;
    private String genre;

    public Boolean isTemperatureInRuleRange(Double temperature) {
        return (minTemperature <= temperature) && (maxTemperature >= temperature);
    }
}
