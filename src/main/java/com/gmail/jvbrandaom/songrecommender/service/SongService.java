package com.gmail.jvbrandaom.songrecommender.service;

import com.gmail.jvbrandaom.songrecommender.domain.PlaylistResponse;
import com.gmail.jvbrandaom.songrecommender.domain.PlaylistSongs;
import com.gmail.jvbrandaom.songrecommender.restclient.SpotifyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SongService {
    @Autowired
    private SpotifyClient spotifyClient;
    @Value("${spotify.api.client.id}")
    private String clientId;
    @Value("${spotify.api.client.secret}")
    private String clientSecret;

    public PlaylistSongs getSongs(String genre) {
        PlaylistResponse playlist = spotifyClient.getPlaylist(genre);
        String playlistSongIds = playlist.getRandomPlaylistId();
        return spotifyClient.getSongsFromPlaylist(playlistSongIds);
    }

    public PlaylistResponse getPlaylist(String genre) {
        return spotifyClient.getPlaylist(genre);
    }
}
