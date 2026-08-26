package com.contatodireto.minstack;

import com.sun.jdi.InvalidTypeException;

import java.util.*;

public class MedianContainer {
    List<Integer> containerValues = new ArrayList<>();

    public void add(int value) {
        containerValues.add(value);
    }
    public boolean delete(int value) {
        if (containerValues.contains(value)) {
            containerValues.remove(Integer.valueOf(value));
            return true;
        } else {
            return false;
        }
    }
    public Integer get_median(List<Integer> list) {
        if (list.isEmpty()) {
            throw new RuntimeException();
        }
        list.sort(Integer::compareTo);
        int size = list.size();
        int half = size / 2;
        if (list.size() % 2 == 1) {
            return list.get(half);
        } else {
            return list.get(half - 1);
        }
    }

    public int get_mode() { // esse metodo eu roubei, nao soube como facilitar ele tive que pesquisar.
        if (containerValues.isEmpty()) {
            throw new RuntimeException();
        }

        int mode = containerValues.get(0);
        int maxCount = 0;

        for (int value : containerValues) {
            int count = 0;

            for (int other : containerValues) {
                if (other == value) {
                    count++;
                }
            }
            if (count > maxCount || (count == maxCount && value < mode)) {
                mode = value;
                maxCount = count;
            }

        }
        return mode;
    }

    public int get_range() {
        if (containerValues.isEmpty()) {
            throw new RuntimeException();
        }
        int minValue = Integer.MAX_VALUE;
        for (int value : containerValues) {
            if (value < minValue) {
                minValue = value;
            }
        }
        int maxValue = Integer.MIN_VALUE;
        for (int value : containerValues) {
            if (value > maxValue) {
                maxValue = value;
            }
        }
        return (maxValue - minValue);
    }
    public int count_above(int threshold) {
        int count = 0;
        for (int values : containerValues) {
            if (values > threshold) {
                count++;
            }
        }
        return count;
    }

    public List<Integer> get_sorted() {
        containerValues.sort(Integer::compareTo);
        return containerValues;
    }

    public List<Integer> get_values_in_range(int min, int max) {
        List<Integer> rangeList = new ArrayList<>();
        for (int value : containerValues) {
            if (value <= max && value >= min) {
                rangeList.add(value);
            }
        }
        return rangeList;
    }

    public int remove_all_below(int threshold) {
        int count = 0;
        Iterator<Integer> it = containerValues.iterator();
        while (it.hasNext()) {
            if (it.next() < threshold) {
                it.remove();
                count++;
            }
        }
        return count;
    }

   /* public int get_median_of_top(int n) {
        if (containerValues.isEmpty()) {
            throw new RuntimeException();
        }
        List<Integer> sorted = new ArrayList<>(containerValues);
        sorted.sort(Collections.reverseOrder());
        int limit = Math.min(n, sorted.size());
        List<Integer> top = sorted.subList(0, limit);
        top.sort(Integer::compareTo);
        int half = top.size() / 2;
        if (top.size() % 2 == 1) {
            return top.get(half);
        } else {
            return top.get(half - 1);
        }
    }*/

    public Map<Integer, Integer> get_frequency_map() {
        //nao sei pegar quantas vezes algo aparece numa lista. so pensei em iterar ela
        //inteira e ir contando se o valor é igual ao int de comparar
    }

    public int get_median_of_top(int n) {
        if (containerValues.isEmpty()) {
            throw new RuntimeException();
        }
        int median = 0;

        if (containerValues.size() < n) {
           median = get_median(containerValues);
        } else {
            if (n > 0) {
                List<Integer> topValues = new ArrayList<>();
                int value = 0;

                List<Integer> sorted = new ArrayList<>(containerValues);
                sorted.sort(Collections.reverseOrder());

                for (int i = 0; i < n; i++) {
                    value = sorted.get(i);
                    topValues.add(value);
                }
                median = get_median(topValues);
            }
        }
        return median;
    }
}
