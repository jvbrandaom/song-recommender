package com.gmail.jvbrandaom.rulesengine;

import com.gmail.jvbrandaom.rulesengine.util.InputStreamToJson;

import java.io.UnsupportedEncodingException;

public class BaseTest {
    public String readTestResource(String path) throws UnsupportedEncodingException {
        return InputStreamToJson.getJson(BaseTest.class.getResourceAsStream(path));
    }

}
