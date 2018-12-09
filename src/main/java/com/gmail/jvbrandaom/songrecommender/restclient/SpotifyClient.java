package com.gmail.jvbrandaom.songrecommender.restclient;

import com.gmail.jvbrandaom.songrecommender.config.SpotifyFeignConfiguration;
import com.gmail.jvbrandaom.songrecommender.domain.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@FeignClient(value = "spotifyClient", url = "${spotify.api.url}", fallback= SpotifyClient.SpotifyClientFallback.class, configuration = SpotifyFeignConfiguration.class)
public interface SpotifyClient {
    @GetMapping(value = "${spotify.api.browse.playlists}")
    PlaylistResponse getPlaylist(@RequestParam("genre") String genre);

    @GetMapping(value = "${spotify.api.playlists}")
    PlaylistSongs getSongsFromPlaylist(@RequestParam("id") String id);

    @Component
    class SpotifyClientFallback implements SpotifyClient {
        @Override
        public PlaylistResponse getPlaylist(String genre) {
            PlaylistResponse playlistResponse = new PlaylistResponse();
            Playlist playlist = new Playlist();
            playlist.setItems(Arrays.asList(new PlaylistItem("37i9dQZF1DXcBWIGoYBM5M", "Fallback playlist")));
            playlistResponse.setPlaylists(playlist);
            return playlistResponse;
        }

        @Override
        public PlaylistSongs getSongsFromPlaylist(String id) {
            PlaylistSongs playlistSongs = new PlaylistSongs();
            Song song1 = new Song("Don't Stop Believin'", "Journey");
            Song song2 = new Song("Never Gonna Give You Up", "Rick Astley");
            Song song3 = new Song("More Than a Feeling", "Boston");
            playlistSongs.setSongs(Arrays.asList(song1, song2, song3));
            return playlistSongs;
        }
    }
}
