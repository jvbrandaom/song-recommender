package com.gmail.jvbrandaom.temperatureapi;

import com.gmail.jvbrandaom.temperatureapi.exception.TemperatureException;
import com.gmail.jvbrandaom.temperatureapi.service.TemperatureService;
import feign.FeignException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TemperatureApiApplicationTests {

	@Autowired
	private TemperatureService temperatureService;
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
}
