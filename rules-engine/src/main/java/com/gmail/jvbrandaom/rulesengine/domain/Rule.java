package com.gmail.jvbrandaom.rulesengine.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rule implements Serializable {
    private Integer minTemperature = Integer.MIN_VALUE;
    private Integer maxTemperature = Integer.MAX_VALUE;
    private String genre;

    public Boolean isTemperatureInRuleRange(Integer temperature) {
        return (minTemperature <= temperature) && (maxTemperature >= temperature);
    }
}
