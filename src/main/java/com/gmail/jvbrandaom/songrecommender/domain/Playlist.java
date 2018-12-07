package com.gmail.jvbrandaom.songrecommender.domain;

import lombok.Data;

import java.util.List;

@Data
public class Playlist {
    private List<PlaylistItem> items;
}
