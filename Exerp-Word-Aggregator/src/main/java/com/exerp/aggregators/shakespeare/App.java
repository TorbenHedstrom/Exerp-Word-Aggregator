package com.exerp.aggregators.shakespeare;

import com.exerp.aggregators.shakespeare.aggregators.SortingAggregator;
import com.exerp.aggregators.shakespeare.aggregators.StreamAggregator;
import com.exerp.aggregators.shakespeare.data.RegExWordProvider;
import com.exerp.aggregators.shakespeare.data.WordProvider;

import java.util.Map;

public class App {
    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        long start = System.currentTimeMillis();

        WordProvider streamWordProvider = new RegExWordProvider("/tempest.txt");
        Map<String, Long> aggregatedWordsByStream = new StreamAggregator(streamWordProvider).aggregatedWords();
        long timeByStream = System.currentTimeMillis() - start;

        System.out.println("Words ordered with streams in: " + timeByStream + " ms. \n" + aggregatedWordsByStream.toString());

        start = System.currentTimeMillis();
        WordProvider sortWordProvider = new RegExWordProvider("/tempest.txt");
        Map<String, Long> aggregatedWordsBySorting = new SortingAggregator(sortWordProvider).aggregatedWords();
        long timeBySorting = System.currentTimeMillis() - start;

        System.out.println("Words ordered with sort in: " + timeBySorting + " ms. \n" + aggregatedWordsBySorting.toString());
    }
}