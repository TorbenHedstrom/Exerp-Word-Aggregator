package com.exerp.aggregators.shakespeare.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExWordProvider implements WordProvider {
    private static String WORD_REGEX = "[\\w'-]+";
    private String fileName;

    public RegExWordProvider(String resourceName) {
        this.fileName = resourceName;
    }

    @Override
    public List<String> words() {
        InputStream resource = RegExWordProvider.class.getResourceAsStream(this.fileName);
        String wordsRaw = null;

        try {
            wordsRaw = new String(resource.readAllBytes(), "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Pattern regExPattern = Pattern.compile(WORD_REGEX);
        Matcher matcher = regExPattern.matcher(wordsRaw);

        ArrayList<String> wordsList = new ArrayList<>();

        while (matcher.find()) {
            String st = matcher.group().toLowerCase();
            wordsList.add(st);
        }

        return wordsList;
    }
}
