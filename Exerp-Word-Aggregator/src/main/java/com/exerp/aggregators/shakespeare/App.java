package com.exerp.aggregators.shakespeare;

import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.exerp.aggregators.shakespeare.data.FileProvider;

public class App {
    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        FileProvider fileProvider = new FileProvider("/tempest.txt");
        List<String> words = null;

        try {
            words = fileProvider.words();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Long> wordsAggregated = words.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Map<String, Long> wordsOrdered = wordsAggregated
                .entrySet()
                .stream()
                .sorted(Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toMap(
                        Entry::getKey,
                        Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        System.out.println(wordsOrdered.toString());
    }
}