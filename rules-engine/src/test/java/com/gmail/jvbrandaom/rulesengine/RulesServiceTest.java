package com.gmail.jvbrandaom.rulesengine;

import com.gmail.jvbrandaom.rulesengine.domain.Rule;
import com.gmail.jvbrandaom.rulesengine.repository.RulesRepository;
import com.gmail.jvbrandaom.rulesengine.service.RulesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
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
    public void testGetSongGenre() throws IOException {
        Rule rule1 = new Rule();
        rule1.setMaxTemperature(0);
        rule1.setGenre("black metal");
        Rule rule2 = new Rule(1, 9, "doom metal");
        Rule rule3 = new Rule(10, 20, "death metal");
        Rule rule4 = new Rule(21, 30, "trash metal");

        when(rulesRepository.getRules()).thenReturn(Arrays.asList(rule1, rule2, rule3, rule4));
        assertEquals("death metal", rulesService.getSongGenre(15));
        assertEquals("trash metal", rulesService.getSongGenre(24));
        assertEquals("doom metal", rulesService.getSongGenre(1));
        assertEquals("black metal", rulesService.getSongGenre(-40));
        assertEquals(RulesService.DEFAULT_GENRE, rulesService.getSongGenre(50));
    }
}
