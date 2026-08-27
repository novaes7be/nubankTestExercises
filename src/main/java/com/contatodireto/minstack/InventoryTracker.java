package com.contatodireto.minstack;

import java.util.*;

public class InventoryTracker {
    Map<String, Integer> inventory = new HashMap<>();

    public void add_stock(String name, int quantity) {
        int currentStock = inventory.getOrDefault(name, 0);
        inventory.put(name, currentStock + quantity);
    }

    public boolean remove_stock(String name, int quantity) {
        if (!inventory.containsKey(name)) {
            return false;
        } else {
            int currentStock = inventory.get(name);
            if (currentStock >= quantity) {
                inventory.put(name, currentStock - quantity);
                return true;
            } else {
                return false;
            }
        }
    }

    public int get_stock(String name) {
        if (!inventory.containsKey(name)) {
            throw new RuntimeException();
        } else {
            return inventory.get(name);
        }
    }

    // end level 1

    public int get_total_stock() {
        int sum = 0;
        for (int valuesInventory : inventory.values()) {
            sum += valuesInventory;
        }
        return sum;
    }

    public String get_lowest_stock_product() {
        if (inventory.isEmpty()) {
            throw new RuntimeException();
        }
        int minValue = Integer.MAX_VALUE;
        for (String valueInventory : inventory.keySet()) {
            if (inventory.get(valueInventory) < minValue) {
                minValue = inventory.get(valueInventory);
            }
        }
        String best = null;
        for (String name : inventory.keySet()) {
            if (inventory.get(name) == minValue) {
                if (best == null || name.compareTo(best) < 0) {
                    best = name;
                }
            }
        }
        return best;
    }

    public int count_out_of_stock() {
        //initialize count
        //iterate the map
        //find value == 0
        //increment count;
        int count = 0;
        for (int valueInventory : inventory.values()) {
            if (valueInventory == 0) {
                count++;
            }
        }
        return count;
    }

    // end level 2

    public List<String> get_products_sorted_by_stock() {
        List<String> names = new ArrayList<>(inventory.keySet());
        names.sort(Comparator.comparingInt(name -> inventory.get(name)));
        return names;

    }

    public List<String> get_products_bellow(int threshold) {
        List<String> productsList = new ArrayList<>();
        for (String product : inventory.keySet()) {
            if (inventory.get(product) < threshold) {
                productsList.add(product);
            }
        }
        return productsList;
    }

    public int restock_all_bellow(int threshold, int amount) {

        int count = 0;
        for (String product : inventory.keySet()) {

            int quantity = inventory.get(product);

            if (inventory.get(product) < threshold) {
                count++;
                inventory.put(product, quantity + amount);
            }
        }
        return count;
    }

    /*
    public List<String> get_top_n_products(int n) {
        List<String> names = new ArrayList<>(inventory.keySet());
        names.sort(Comparator.comparingInt(name -> inventory.get(name)).reversed());
        if (n > inventory.size()) {
            return names;
        } else {
            return
        }
        return names;


    }*/
}
