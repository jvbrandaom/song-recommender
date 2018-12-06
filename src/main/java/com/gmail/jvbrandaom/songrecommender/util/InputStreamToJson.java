package com.gmail.jvbrandaom.songrecommender.util;



import org.apache.commons.lang.CharEncoding;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.stream.Collectors;

public class InputStreamToJson {

    public static String getJson(InputStream inputStream) throws UnsupportedEncodingException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream, CharEncoding.UTF_8));
        return buffer.lines().collect(Collectors.joining("\n"));
    }
}
