package com.gmail.jvbrandaom.songrecommender.restclient;

import com.gmail.jvbrandaom.songrecommender.domain.PlaylistResponse;
import com.gmail.jvbrandaom.songrecommender.domain.PlaylistSongs;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;


@FeignClient(value = "spotifyClient", url = "${spotify.api.url}")
public interface SpotifyClient {
    @GetMapping(value = "${spotify.api.browse.playlists}")
    PlaylistResponse getPlaylist(@RequestParam("genre") String genre, @RequestHeader(HttpHeaders.AUTHORIZATION) String token);

    @GetMapping(value = "${spotify.api.playlists}")
    PlaylistSongs getSongsFromPlaylist(@RequestParam("id") String id, @RequestHeader(HttpHeaders.AUTHORIZATION) String token);
}
