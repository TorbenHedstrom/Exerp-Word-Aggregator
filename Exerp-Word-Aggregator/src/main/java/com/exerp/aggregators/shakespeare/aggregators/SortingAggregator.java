package com.exerp.aggregators.shakespeare.aggregators;

import com.exerp.aggregators.shakespeare.data.WordProvider;

import java.util.*;

public class SortingAggregator implements WordAggregator {
    private WordProvider wordProvider;;

    public SortingAggregator(WordProvider wordProvider) {
        this.wordProvider = wordProvider;
    }

    @Override
    public Map<String, Long> aggregatedWords() {
        if (wordProvider.words().isEmpty()) {
            System.out.println("No words to aggregate... ");
            return new HashMap<>();
        }

        // Sort words
        List<String> words = wordProvider.words();
        words.sort(Comparator.naturalOrder());
        List<Item> items = new LinkedList<>();

        //... And aggregate
        String ref = words.getFirst();
        Long cnt = 0l;

        int i = 0;
        for (String str : words) {
            if (ref.equals(str)) {
                cnt++;
            } else {
                items.add(new Item(ref, cnt));
                ref = str;
                cnt = 1l;
            }
            i++;
        }

        items.sort( (i1, i2) -> i1.value > i2.value ? -1 : i1.value == i2.value ? 0 : 1);

        LinkedHashMap<String, Long> result = new LinkedHashMap<>();
        Integer index = 0;

        for (Item item : items.subList(0, 10)) {
            result.put(item.key, item.value);
        }

        return result;
    }

    private static class Item {
        private String key;
        private Long value;

        public Item(String key, Long value) {
            this.key = key;
            this.value = value;
        }
    }
}
