package com.gmail.jvbrandaom.songrecommender.domain;

import lombok.Data;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Data
public class PlaylistResponse {
    private Playlist playlists;

    public String getRandomPlaylistId() {
        List<String> playlistIds = getPlaylistIds();
        return playlistIds.get(new Random().nextInt(playlistIds.size()));
    }

    private List<String> getPlaylistIds() {
        return playlists.getItems().stream().map(PlaylistItem::getId).collect(Collectors.toList());
    }
}
