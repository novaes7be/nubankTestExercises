package com.contatodireto.minstack;

import java.util.HashMap;
import java.util.Map;

public class WordCounter {
    Map<String, Integer> wordCounter = new HashMap<>();

    public void add(String word) {
        if (wordCounter.containsKey(word)) {
            int quantity = wordCounter.get(word);
            wordCounter.put(word, ++quantity);
        } else {
            wordCounter.put(word, 1);
        }
    }

    public boolean remove(String word) {
        if (wordCounter.containsKey(word) && wordCounter.get(word) > 0) {
            int quantity = wordCounter.get(word);
            wordCounter.put(word, --quantity);
            if (quantity == 0){
                wordCounter.remove(word);
            }
            return true;
        } else {
            return false;
        }
    }

    public int get_count(String word) {
        if (!wordCounter.containsKey(word)) {
            throw new RuntimeException("This word has never been added");
        } else {
            return wordCounter.get(word);
        }
    }
}
