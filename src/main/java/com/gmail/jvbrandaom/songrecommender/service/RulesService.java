package com.gmail.jvbrandaom.songrecommender.service;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.gmail.jvbrandaom.songrecommender.domain.Rule;
import com.gmail.jvbrandaom.songrecommender.exception.RuleParsingException;
import com.gmail.jvbrandaom.songrecommender.repository.RulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RulesService {
    @Autowired
    private RulesRepository rulesRepository;

    public final static String DEFAULT_GENRE = "pop";

    public String getSongGenre(Double temperature) {
        try {
            List<Rule> rules = rulesRepository.getRules();
            return rules.stream().filter(rule -> rule.isTemperatureInRuleRange(temperature)).map(Rule::getGenre).findFirst().orElse(DEFAULT_GENRE);
        } catch (RuleParsingException | AmazonS3Exception e) {
            e.printStackTrace();
            return DEFAULT_GENRE;
        }
    }
}
