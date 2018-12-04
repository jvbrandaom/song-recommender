package com.gmail.jvbrandaom.rulesengine;

import com.gmail.jvbrandaom.rulesengine.domain.Rule;
import com.gmail.jvbrandaom.rulesengine.repository.RulesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RulesRepositoryTest extends BaseTest {

    @InjectMocks
    private RulesRepository rulesRepository;

    @Test
    public void testParseRules() throws IOException {
        String rules = readTestResource("/rules.json");
        List<Rule> rulesList = rulesRepository.parseRules(rules);
        assertEquals(4, rulesList.size());
    }
}
