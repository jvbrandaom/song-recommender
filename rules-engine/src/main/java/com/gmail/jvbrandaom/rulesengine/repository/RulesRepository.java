package com.gmail.jvbrandaom.rulesengine.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.jvbrandaom.rulesengine.domain.Rule;
import com.gmail.jvbrandaom.rulesengine.exception.RuleParsingException;
import com.gmail.jvbrandaom.rulesengine.util.InputStreamToJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class RulesRepository {
    @Autowired
    private ResourceLoader resourceLoader;

    private ObjectMapper objectMapper = new ObjectMapper();

    public List<Rule> getRules() throws RuleParsingException {
        try {
            // TODO: put bucket location in application properties
            Resource resource = resourceLoader.getResource("s3://song-recommender/rules.json");
            String rules = InputStreamToJson.getJson(resource.getInputStream());
            return parseRules(rules);
        } catch (IOException e) {
            throw new RuleParsingException("There was an error parsing the rules");
        }

    }

    public List<Rule> parseRules(String rules) throws IOException {
        return Arrays.asList(objectMapper.readValue(rules, Rule[].class));
    }
}
