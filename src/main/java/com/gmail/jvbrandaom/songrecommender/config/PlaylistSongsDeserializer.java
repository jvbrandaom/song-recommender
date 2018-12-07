package com.gmail.jvbrandaom.songrecommender.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.gmail.jvbrandaom.songrecommender.domain.PlaylistSongs;
import com.gmail.jvbrandaom.songrecommender.domain.Song;

import java.io.IOException;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

public class PlaylistSongsDeserializer extends JsonDeserializer<PlaylistSongs> {
    @Override
    public PlaylistSongs deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        PlaylistSongs playlistSongs = new PlaylistSongs();

        ObjectCodec oc = jp.getCodec();
        JsonNode node = oc.readTree(jp);
        JsonNode items = node.get("items");
        items.forEach(item -> {
            JsonNode track = item.get("track");
            String name = track.get("name").textValue();
            JsonNode artists = track.get("artists");
            String artist = IntStream.range(0, artists.size()).mapToObj(artists::get).map(a -> a.get("name").textValue()).collect(joining(" & "));
            playlistSongs.getSongs().add(new Song(name, artist));
        });

        return playlistSongs;
    }
}
