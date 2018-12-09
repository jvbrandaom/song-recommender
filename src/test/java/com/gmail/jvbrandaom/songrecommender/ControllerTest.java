package com.gmail.jvbrandaom.songrecommender;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {SongRecommenderApplication.class})
public class ControllerTest {

    @Autowired
    protected WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup() throws IOException {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getPlaylistFromCity() throws Exception {
        this.mockMvc.perform(get("/playlist/city/Campinas"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("name")))
                .andExpect(content().string(containsString("artist")));
    }

    @Test
    public void getPlaylistFromCoordinates() throws Exception {
        this.mockMvc.perform(get("/playlist/latitude/50/longitude/50"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("name")))
                .andExpect(content().string(containsString("artist")));
    }
}
