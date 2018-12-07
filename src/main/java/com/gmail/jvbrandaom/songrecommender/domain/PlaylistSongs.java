package com.gmail.jvbrandaom.songrecommender.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

@Data
public class PlaylistSongs {

    private List<Song> songs = new ArrayList<>();

    @SuppressWarnings("unchecked")
    @JsonProperty("items")
    private void parse(List<Map<String, Object>> items) {
        items.forEach(item -> {
            Map<String,Object> track = (Map<String, Object>) item.get("track");
            String songName = (String) track.get("name");
            List<Map<String, Object>> artists = (List<Map<String, Object>>) track.get("artists");
            String artistName = artists.stream().map(artist -> (String) artist.get("name")).collect(joining(" &"));
            songs.add(new Song(songName, artistName));
        });
    }
}
