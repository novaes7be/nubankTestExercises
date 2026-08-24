package com.contatodireto.minstack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Poll {
    Map<String, Integer> votes = new HashMap<>();

    public void vote(String option) {
        int current = votes.getOrDefault(option, 0);
        votes.put(option, current + 1);
    }

    public int get_votes(String option) {
        if (!votes.containsKey(option)) {
            throw new RuntimeException();
        }
        return votes.get(option);
    }

    public boolean remove_vote(String option) {
        if (!votes.containsKey(option) || votes.get(option) == 0) {
            return false;
        }
        int current = votes.get(option);
        votes.put(option, current - 1);
        return true;
    }

    public String get_winner() {
        if (votes.isEmpty()) {
            throw new RuntimeException();
        } else {
            String getKey = "";
            int high = - 1;
            for (Map.Entry<String, Integer> c : votes.entrySet()) {
                if (c.getValue() > high) {
                    high = c.getValue();
                    getKey = c.getKey();
                }
            }
            return getKey;
        }
    }

    public int get_total_votes() {
        int sum = 0;
        for (int i : votes.values()) {
            sum += i;
        }
        return sum;
    }

    public List<String> get_options_above(int threshold) {
        List<String> aboveThreshold = new ArrayList<>();

        String getKey = "";

        for (Map.Entry<String, Integer> c : votes.entrySet()) {
            if (c.getValue() > threshold) {
                getKey = c.getKey();
                aboveThreshold.add(getKey);
            }
        }
        return aboveThreshold;
    }
}
