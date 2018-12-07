package com.gmail.jvbrandaom.songrecommender.service;

import com.gmail.jvbrandaom.songrecommender.domain.PlaylistResponse;
import com.gmail.jvbrandaom.songrecommender.domain.PlaylistSongs;
import com.gmail.jvbrandaom.songrecommender.domain.Token;
import com.gmail.jvbrandaom.songrecommender.restclient.SpotifyAuthorizationClient;
import com.gmail.jvbrandaom.songrecommender.restclient.SpotifyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SongService {

    @Autowired
    private SpotifyAuthorizationClient authorizationClient;
    @Autowired
    private SpotifyClient spotifyClient;
    @Value("${spotify.api.client.id}")
    private String clientId;
    @Value("${spotify.api.client.secret}")
    private String clientSecret;

    public PlaylistSongs getSongs(String genre) {
        String token = getToken().toString();
        PlaylistResponse playlist = spotifyClient.getPlaylist(genre, token);
        List<String> playlistSongIds = playlist.getPlaylistSongIds();
        return spotifyClient.getSongsFromPlaylist(playlistSongIds.get(0), token);
    }

    public PlaylistResponse getPlaylist(String genre) {
        return spotifyClient.getPlaylist(genre, getToken().toString());
    }

    public Token getToken() {
        Map<String, String> body = new HashMap<>();
        body.put("grant_type", "client_credentials");
        return authorizationClient.getToken(body, getEncodedAuthorizationString());
    }

    private String getEncodedAuthorizationString() {
        String authorizationString = String.format("%s:%s", clientId, clientSecret);
        String encodedAuthorization = new String(Base64.getEncoder().encode(authorizationString.getBytes()));
        return String.format("Basic %s", encodedAuthorization);
    }
}
