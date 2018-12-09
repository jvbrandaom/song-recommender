package com.gmail.jvbrandaom.songrecommender.controller;

import com.gmail.jvbrandaom.songrecommender.domain.Song;
import com.gmail.jvbrandaom.songrecommender.exception.RuleParsingException;
import com.gmail.jvbrandaom.songrecommender.exception.TemperatureException;
import com.gmail.jvbrandaom.songrecommender.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @GetMapping("/playlist/city/{city}")
    private List<Song> getPlaylistFromCity(@PathVariable String city) throws TemperatureException, RuleParsingException {
        return playlistService.getPlaylist(city).getSongs();
    }

    @GetMapping("/playlist/latitude/{latitude}/longitude/{longitude}")
    private List<Song> getPlaylist(@PathVariable Double latitude, @PathVariable Double longitude) throws TemperatureException, RuleParsingException {
        return playlistService.getPlaylist(latitude, longitude).getSongs();
    }
}
