package com.exerp.aggregators.shakespeare.data;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileProvider {
    private static String WORD_REGEX = "[\\w'-]+";
    private String fileName;

    public FileProvider(String resourceName) {
        this.fileName = resourceName;
    }

    public List<String> words() throws IOException {
        InputStream resource = FileProvider.class.getResourceAsStream(this.fileName);

        String s = new String(resource.readAllBytes(), "UTF-8");

        Pattern regExPattern = Pattern.compile(WORD_REGEX);
        Matcher matcher = regExPattern.matcher(s);

        ArrayList<String> words = new ArrayList<>();

        while (matcher.find()) {
            String st = matcher.group().toLowerCase();
            words.add(st);
        }

        return words;
    }
}
