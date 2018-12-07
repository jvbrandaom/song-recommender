package com.gmail.jvbrandaom.songrecommender.controller;

import com.gmail.jvbrandaom.songrecommender.domain.PlaylistSongs;
import com.gmail.jvbrandaom.songrecommender.exception.RuleParsingException;
import com.gmail.jvbrandaom.songrecommender.exception.TemperatureException;
import com.gmail.jvbrandaom.songrecommender.service.RulesService;
import com.gmail.jvbrandaom.songrecommender.service.SongService;
import com.gmail.jvbrandaom.songrecommender.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlaylistController {

    @Autowired
    private SongService songService;
    @Autowired
    private RulesService rulesService;
    @Autowired
    private TemperatureService temperatureService;

    @GetMapping("/playlist/{city}")
    private PlaylistSongs getPlaylist(@PathVariable String city) throws TemperatureException, RuleParsingException {
        Double temperatureFromCity = temperatureService.getTemperatureFromCity(city);
        String songGenre = rulesService.getSongGenre(temperatureFromCity);
        return songService.getSongs(songGenre);
    }
}
