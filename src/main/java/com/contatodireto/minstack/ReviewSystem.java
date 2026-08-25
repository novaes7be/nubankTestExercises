package com.contatodireto.minstack;

import java.util.*;

public class ReviewSystem {
    Map<String, List<Integer>> reviews = new HashMap<>();

    public void add_review(String product, int rating) {
        if (rating < 1 || rating > 5) {
            throw new RuntimeException("Rating must be between 1 and 5");
        }
        reviews.computeIfAbsent(product, k -> new ArrayList<>()).add(rating);
    }

    public int get_review_count(String product) {
        if (!reviews.containsKey(product)) {
            throw new RuntimeException();
        }
        return reviews.get(product).size();
    }

    public double get_average_rating(String product) {
        if (!reviews.containsKey(product) || reviews.get(product).isEmpty()) {
            throw new RuntimeException();
        }
        double sum = 0;
        for (int r : reviews.get(product)) {
            sum += r;
        }
        return sum / reviews.get(product).size();
    }

    public String get_best_product() {
        if (reviews.isEmpty()) {
            throw new RuntimeException("There's no review for this product");
        } else {
            String bestProduct = "";
            double bestAverage = -1;

            for (Map.Entry<String, List<Integer>> entry : reviews.entrySet()) {
                String product = entry.getKey();
                double average = get_average_rating(product);

                if (average > bestAverage) {
                    bestAverage = average;
                    bestProduct = product;
                }
            }
            return bestProduct;
        }
    }

    public double get_total_reviews() {
        if (reviews.isEmpty()) {
            throw new RuntimeException();
        }
        int sum = 0;
        int index = 0;
        for (List<Integer> r : reviews.values()) {
            sum += r.size();
            index++;
        }
        return sum;
    }

    public List<String> get_products_with_min_reviews(int minCount) {
        String product = "";
        List<String> names = new ArrayList<>();
        for (Map.Entry<String, List<Integer>> i : reviews.entrySet()) {
                product = i.getKey();
                if (i.getValue().size() >= minCount) {
                names.add(product);
            }
        }
        return names;
    }
}