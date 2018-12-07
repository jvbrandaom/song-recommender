package com.gmail.jvbrandaom.songrecommender.restclient;

import com.gmail.jvbrandaom.songrecommender.domain.Playlist;
import com.gmail.jvbrandaom.songrecommender.domain.PlaylistResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;


@FeignClient(value = "spotifyClient", url = "${spotify.api.url}")
public interface SpotifyClient {
    @GetMapping(value = "${spotify.api.playlist}")
    PlaylistResponse getPlaylist(@RequestParam("genre") String genre, @RequestHeader(HttpHeaders.AUTHORIZATION) String token);
}
