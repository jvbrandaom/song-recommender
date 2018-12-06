package com.gmail.jvbrandaom.songrecommender;

import com.gmail.jvbrandaom.songrecommender.domain.Rule;
import com.gmail.jvbrandaom.songrecommender.domain.Token;
import com.gmail.jvbrandaom.songrecommender.exception.RuleParsingException;
import com.gmail.jvbrandaom.songrecommender.exception.TemperatureException;
import com.gmail.jvbrandaom.songrecommender.repository.RulesRepository;
import com.gmail.jvbrandaom.songrecommender.service.SongService;
import com.gmail.jvbrandaom.songrecommender.service.TemperatureService;
import feign.FeignException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SongRecommenderApplicationTests {

	@Autowired
	private TemperatureService temperatureService;
	@Autowired
	private RulesRepository rulesRepository;
	@Autowired
	private SongService songService;

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

	@Test(expected = FeignException.class)
	public void getTemperatureFromCityError() throws TemperatureException {
		temperatureService.getTemperatureFromCity("R'lyeh");
	}

	@Test
	public void getTemperatureFromCoordinates() throws TemperatureException {
		Double temperature = temperatureService.getTemperatureFromCoordinates(35.0, 35.0);
		assertNotNull(temperature);
	}

	@Test(expected = FeignException.class)
	public void getTemperatureFromCoordinatesError() throws TemperatureException {
		Double temperature = temperatureService.getTemperatureFromCoordinates(Double.MAX_VALUE, 35.0);
		assertNotNull(temperature);
	}

	@Test
	public void getSongApiToken() throws TemperatureException {
		Token token = songService.getToken();
		System.out.println(token);
		assertNotNull(token);
	}
}
