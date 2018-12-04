package com.gmail.jvbrandaom.rulesengine;

import com.gmail.jvbrandaom.rulesengine.domain.Rule;
import com.gmail.jvbrandaom.rulesengine.repository.RulesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RulesEngineApplicationTests {

	@Autowired
	private RulesRepository rulesRepository;

	@Test
	public void testGetRules() throws IOException {
		List<Rule> rules = rulesRepository.getRules();
		assertTrue(rules.size() > 0);
	}

}
