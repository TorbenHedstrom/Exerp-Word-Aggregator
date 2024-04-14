package com.exerp.aggregators.shakespeare.aggregators;

import com.exerp.aggregators.shakespeare.data.WordProvider;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamAggregator implements WordAggregator {
    private WordProvider wordProvider;

    public StreamAggregator(WordProvider wordProvider) {
        this.wordProvider = wordProvider;
    }

    @Override
    public Map<String, Long> aggregatedWords() {
        // Aggregate words
        Map<String, Long> wordsAggregated = wordProvider
                .words()
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        // ... And sort -> Return
        return wordsAggregated
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
}
