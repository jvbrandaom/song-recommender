package com.gmail.jvbrandaom.songrecommender;

import com.gmail.jvbrandaom.songrecommender.util.InputStreamToJson;

import java.io.UnsupportedEncodingException;

public class BaseTest {
    public String readTestResource(String path) throws UnsupportedEncodingException {
        return InputStreamToJson.getJson(BaseTest.class.getResourceAsStream(path));
    }

}
