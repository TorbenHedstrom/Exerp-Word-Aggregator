package com.exerp.aggregators.shakespeare.aggregators;

import java.util.Map;

public interface WordAggregator {
    Map<String, Long> aggregatedWords();
}
