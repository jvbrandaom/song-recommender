package com.gmail.jvbrandaom.songrecommender.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gmail.jvbrandaom.songrecommender.config.PlaylistSongsDeserializer;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonDeserialize(using = PlaylistSongsDeserializer.class)
public class PlaylistSongs {
    private List<Song> songs = new ArrayList<>();
}
