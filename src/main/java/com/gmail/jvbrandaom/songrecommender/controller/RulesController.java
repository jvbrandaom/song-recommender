package com.gmail.jvbrandaom.songrecommender.controller;

import com.gmail.jvbrandaom.songrecommender.exception.RuleParsingException;
import com.gmail.jvbrandaom.songrecommender.service.RulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RulesController {

    @Autowired
    private RulesService rulesService;

    @GetMapping("/song/genre/{temperature}")
    public String getGenreBasedOnTemperature(@PathVariable Double temperature) throws RuleParsingException {
        return rulesService.getSongGenre(temperature);
    }
}
