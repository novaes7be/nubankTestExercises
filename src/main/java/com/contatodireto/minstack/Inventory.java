package com.contatodireto.minstack;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    String product;
    int quantity;

    public Inventory() {}

    Map<String, Integer> productsMap = new HashMap<>();

    public void addStock(String product, int quantity) {
        if (quantity > 0) {
            productsMap.put(product,quantity);
        } else throw new RuntimeException("Invalid quantity");
    }

    public void removeStock(String product, int quantity) {
        if (quantity <= 0 || productsMap.isEmpty() || quantity > productsMap.get(product)) {
            throw new RuntimeException();
        } else {
            int removeOperation = productsMap.get(product) - quantity;
            productsMap.put(product, removeOperation);
        }
    }
    public int getStock(String product) {
        if (productsMap.isEmpty()) {
            throw new RuntimeException();
        } else {
            return productsMap.get(product);
        }
    }
}
