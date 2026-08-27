package com.contatodireto.minstack;

import java.util.*;

public class UserTracking {
    Map<String, Integer> scoresMap = new HashMap<>();

    public void add(String user, int points) {
        int defaultValue = scoresMap.getOrDefault(user, 0);
        if (!scoresMap.containsKey(user)) {
            scoresMap.put(user, points);
        } else {
            scoresMap.put(user, defaultValue + points);
        }
    }

    public boolean reset_score(String user) {
        if (scoresMap.containsKey(user)) {
            scoresMap.put(user, 0);
            return true;
        } else {
            return false;
        }
    }

    public int get_score(String user) {
        if (!scoresMap.containsKey(user)) {
            throw new RuntimeException();
        } else {
            return scoresMap.get(user);
        }
    }

    public double get_average_score() {
        if (scoresMap.isEmpty()) {
            throw new RuntimeException();
        } else {
            double sum = 0;
            for (int anyValue : scoresMap.values()) {
                sum += anyValue;
            }
            return sum / scoresMap.size();
        }
    }

    public String get_top_score() {
        if (scoresMap.isEmpty()) {
            throw new RuntimeException();
        }
        String best = null;
        for (String anyString : scoresMap.keySet()) {
            if (best == null) {
                best = anyString;
            } else if (scoresMap.get(anyString) > scoresMap.get(best)) {
                best = anyString;
            } else if (scoresMap.get(anyString) == scoresMap.get(best)) {
                if (anyString.compareTo(best) < 0) {
                    best = anyString;
                }
            }
        }
        return best;
    }

    //u did not say the return type so i will judge that it is integer
    public int count_zero_scores() {
        int count = 0;
        for (int anyValue : scoresMap.values()) {
            if (anyValue == 0) {
                count++;
            }
        }
        return count;
    }

    public List<String> get_users_sorted_by_score() {
        List<String> sorted = new ArrayList<>(scoresMap.keySet());
        sorted.sort(Comparator.comparingInt(user -> scoresMap.get(user)));
        return sorted;
    }

    public List<String> get_users_below(int threshold) {
        List<String> belowThreshold = new ArrayList<>();
        for (String anyString : scoresMap.keySet()) {
            if (scoresMap.get(anyString) < threshold) {
                belowThreshold.add(anyString);
            }
        }
        return belowThreshold;
    }

    public int penalize_all_above(int threshold, int penalty) {
        int count = 0;
        for (String anyString: scoresMap.keySet()) {
            if (scoresMap.get(anyString) > threshold && (scoresMap.get(anyString) - penalty) > 0) {
                scoresMap.put(anyString, scoresMap.get(anyString) - penalty);
                count++;
            }
        }
        return count;
    }

    public List<String> get_top_n_users(int n) {
        List<String> sorted = new ArrayList<>(scoresMap.keySet());
        sorted.sort(Collections.reverseOrder());
        List<String> storeSorted = new ArrayList<>();
        if (sorted.size() < n) {
            return sorted;
        }
        for (int i = 0; i < n; i++) {
            storeSorted.add(sorted.get(i));
        }
        return storeSorted;
    }

    public boolean transfer_score(String from, String to, int amount) {
        if (!scoresMap.containsKey(from) || !scoresMap.containsKey(to) || scoresMap.get(from) < amount) {
            return false;
        } else {
            scoresMap.put(from, scoresMap.get(from) - amount);
            scoresMap.put(to, scoresMap.get(to) + amount);
            return true;
        }
    }
}
