package com.gmail.jvbrandaom.songrecommender;

import com.gmail.jvbrandaom.songrecommender.domain.Rule;
import com.gmail.jvbrandaom.songrecommender.exception.RuleParsingException;
import com.gmail.jvbrandaom.songrecommender.repository.RulesRepository;
import com.gmail.jvbrandaom.songrecommender.service.RulesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class RulesServiceTest extends BaseTest {

    @InjectMocks
    private RulesService rulesService;
    @Mock
    private RulesRepository rulesRepository;

    @Test
    public void testGetSongGenre() throws RuleParsingException {
        Rule rule1 = new Rule();
        rule1.setMaxTemperature(0.0);
        rule1.setGenre("black metal");
        Rule rule2 = new Rule(1.0, 9.5, "doom metal");
        Rule rule3 = new Rule(10.0, 20.0, "death metal");
        Rule rule4 = new Rule(21.78, 30.0, "trash metal");

        when(rulesRepository.getRules()).thenReturn(Arrays.asList(rule1, rule2, rule3, rule4));
        assertEquals("death metal", rulesService.getSongGenre(15.0));
        assertEquals("trash metal", rulesService.getSongGenre(24.0));
        assertEquals("doom metal", rulesService.getSongGenre(1.0));
        assertEquals("black metal", rulesService.getSongGenre(-40.0));
        assertEquals(RulesService.DEFAULT_GENRE, rulesService.getSongGenre(50.0));
    }

    @Test
    public void testGetSongGenreOnBoundaries() throws RuleParsingException {
        Rule rule1 = new Rule();
        rule1.setMaxTemperature(10.0);
        rule1.setGenre("black metal");
        Rule rule2 = new Rule(10.0, 15.0, "doom metal");
        Rule rule3 = new Rule(15.0, 30.0, "death metal");
        Rule rule4 = new Rule();
        rule4.setGenre("thrash metal");
        rule4.setMinTemperature(30.0);

        when(rulesRepository.getRules()).thenReturn(Arrays.asList(rule1, rule2, rule3, rule4));
        assertEquals("black metal", rulesService.getSongGenre(9.99999999999));
        assertEquals("doom metal", rulesService.getSongGenre(10.0));
        assertEquals("doom metal", rulesService.getSongGenre(14.999999));
        assertEquals("death metal", rulesService.getSongGenre(15.0));
        assertEquals("death metal", rulesService.getSongGenre(29.9999999));
        assertEquals("thrash metal", rulesService.getSongGenre(30.0));
    }
}
