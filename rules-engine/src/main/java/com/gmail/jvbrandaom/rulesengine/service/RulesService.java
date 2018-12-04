package com.gmail.jvbrandaom.rulesengine.service;

import com.gmail.jvbrandaom.rulesengine.domain.Rule;
import com.gmail.jvbrandaom.rulesengine.repository.RulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RulesService {
    @Autowired
    private RulesRepository rulesRepository;

    public final static String DEFAULT_GENRE = "pop";

    public String getSongGenre(Integer temperature) {
        List<Rule> rules = rulesRepository.getRules();
        return rules.stream().filter(rule -> rule.isTemperatureInRuleRange(temperature)).map(Rule::getGenre).findFirst().orElse(DEFAULT_GENRE);
    }
}