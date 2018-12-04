package com.gmail.jvbrandaom.rulesengine;

import org.apache.commons.codec.CharEncoding;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.stream.Collectors;

public class BaseTest {
    public String readTestResource(String path) throws UnsupportedEncodingException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(BaseTest.class.getResourceAsStream(path), CharEncoding.UTF_8));
        return buffer.lines().collect(Collectors.joining("\n"));
    }

}
