package com.gmail.jvbrandaom.songrecommender.domain;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PlaylistResponse {
    private Playlist playlists;

    public List<String> getPlaylistSongIds() {
        return playlists.getItems().stream().map(PlaylistItem::getId).collect(Collectors.toList());
    }
}
