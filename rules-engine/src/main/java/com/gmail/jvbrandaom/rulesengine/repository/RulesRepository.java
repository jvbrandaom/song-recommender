package com.gmail.jvbrandaom.rulesengine.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.jvbrandaom.rulesengine.domain.Rule;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class RulesRepository {

    private ObjectMapper objectMapper = new ObjectMapper();

    // TODO: implement
    public List<Rule> getRules() {
        return null;
    }

    public List<Rule> parseRules(String rules) throws IOException {
        return Arrays.asList(objectMapper.readValue(rules, Rule[].class));
    }
}
