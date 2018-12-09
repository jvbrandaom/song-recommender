package com.gmail.jvbrandaom.songrecommender;

import com.gmail.jvbrandaom.songrecommender.domain.*;
import com.gmail.jvbrandaom.songrecommender.exception.RuleParsingException;
import com.gmail.jvbrandaom.songrecommender.exception.TemperatureException;
import com.gmail.jvbrandaom.songrecommender.repository.RulesRepository;
import com.gmail.jvbrandaom.songrecommender.restclient.SpotifyClient;
import com.gmail.jvbrandaom.songrecommender.service.PlaylistService;
import com.gmail.jvbrandaom.songrecommender.service.SongService;
import com.gmail.jvbrandaom.songrecommender.service.TemperatureService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTests {

	@Autowired
	private TemperatureService temperatureService;
	@Autowired
	private RulesRepository rulesRepository;
	@Autowired
	private SongService songService;
	@Autowired
	private SpotifyClient spotifyClient;
	@Autowired
	private PlaylistService playlistService;

	@Test
	public void testGetRules() throws RuleParsingException {
		List<Rule> rules = rulesRepository.getRules();
		assertTrue(rules.size() > 0);
	}

	@Test
	public void getTemperatureFromCity() throws TemperatureException {
		Double temperature = temperatureService.getTemperatureFromCity("Campinas");
		assertNotNull(temperature);
	}

	@Test
	public void getTemperatureFromCityError() {
		Double temperatureFromCity = temperatureService.getTemperatureFromCity("R'lyeh");
		assertThat(24.9999).isEqualTo(temperatureFromCity);
	}

	@Test
	public void getTemperatureFromCoordinates() {
		Double temperature = temperatureService.getTemperatureFromCoordinates(35.0, 35.0);
		assertNotNull(temperature);
	}

	public void getTemperatureFromCoordinatesError() {
		Double temperature = temperatureService.getTemperatureFromCoordinates(Double.MAX_VALUE, 35.0);
		assertThat(24.9999).isEqualTo(temperature);
	}

	@Test
	public void getPlaylist() {
		PlaylistResponse playlistResponse = songService.getPlaylist("party");
		String playlistName = playlistResponse.getPlaylists().getItems().get(0).getName();
		System.out.println(playlistName);
		assertNotNull(playlistResponse);
		assertNotEquals("Fallback playlist", playlistName);
	}

	@Test
	public void getPlaylistFallback() {
		PlaylistResponse playlistResponse = songService.getPlaylist("very underground swedish death metal");
		PlaylistItem playlistItem = playlistResponse.getPlaylists().getItems().get(0);
		assertTrue("Fallback playlist".equalsIgnoreCase(playlistItem.getName()));
		assertNotNull(playlistResponse);
	}

	@Test
	public void getPlaylistForAllGenresFromRules() throws RuleParsingException {
		List<Rule> rules = rulesRepository.getRules();
		rules.forEach(rule -> {
			PlaylistResponse playlistResponse = songService.getPlaylist(rule.getGenre());
			System.out.println(playlistResponse.getPlaylists().getItems().get(0).getName());
			assertNotNull(playlistResponse);
		});
	}

	@Test
	public void getSongsFromPlaylist() throws RuleParsingException {
		PlaylistSongs playlistSongs = songService.getSongs("pop");
		assertTrue(playlistSongs.getSongs().size() > 0);
	}

	@Test
	public void getPlaylistSongsForAllGenresFromRules() throws RuleParsingException {
		List<Rule> rules = rulesRepository.getRules();
		rules.forEach(rule -> {
			PlaylistSongs playlistSongs = songService.getSongs(rule.getGenre());
			System.out.println(playlistSongs);
			assertTrue(playlistSongs.getSongs().size() > 0);
		});
	}

	@Test
	public void getSongsFromFallback() throws RuleParsingException {
		PlaylistSongs playlistSongs = spotifyClient.getSongsFromPlaylist("whatever");
		assertThat(playlistSongs.getSongs().stream().map(Song::getArtist)).containsExactlyInAnyOrder("Journey", "Rick Astley", "Boston");
	}
}
