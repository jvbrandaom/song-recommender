package com.gmail.jvbrandaom.songrecommender.service;

import com.gmail.jvbrandaom.songrecommender.domain.PlaylistSongs;
import com.gmail.jvbrandaom.songrecommender.exception.RuleParsingException;
import com.gmail.jvbrandaom.songrecommender.exception.TemperatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaylistService {
    @Autowired
    private SongService songService;
    @Autowired
    private RulesService rulesService;
    @Autowired
    private TemperatureService temperatureService;

    public PlaylistSongs getPlaylist(String city) throws TemperatureException, RuleParsingException {
        Double temperature = temperatureService.getTemperatureFromCity(city);
        return getPlaylistBasedOnTemperature(temperature);
    }

    public PlaylistSongs getPlaylist(Double latitude, Double longitude) throws TemperatureException, RuleParsingException {
        Double temperatureFromCity = temperatureService.getTemperatureFromCoordinates(latitude, longitude);
        return getPlaylistBasedOnTemperature(temperatureFromCity);
    }

    private PlaylistSongs getPlaylistBasedOnTemperature(Double temperature) throws RuleParsingException {
        String songGenre = rulesService.getSongGenre(temperature);
        return songService.getSongs(songGenre);
    }
}
