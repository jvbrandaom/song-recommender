package com.gmail.jvbrandaom.songrecommender.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.jvbrandaom.songrecommender.domain.Rule;
import com.gmail.jvbrandaom.songrecommender.exception.RuleParsingException;
import com.gmail.jvbrandaom.songrecommender.util.InputStreamToJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    private ObjectMapper objectMapper = new ObjectMapper();

    public List<Rule> getRules() throws RuleParsingException {
        try {
            Resource resource = resourceLoader.getResource(String.format("s3://%s/rules.json", bucketName));
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
