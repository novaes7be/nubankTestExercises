package com.contatodireto.minstack;

import java.util.*;

public class OrderSystem {

    Map<String,Item> orders = new HashMap<>();

    public void addItem(String name, double price) {
        Item item = new Item(name, price);
        orders.put(name, item);
    }
    public boolean removeItem(String name) {
         return orders.remove(name) != null;
    }
    public double getTotal() {
        double sum = 0;
        if (orders.isEmpty()) throw new RuntimeException();
        for (Item i: orders.values()) {
            sum += i.getPrice();
        }
        return sum;
    }

    //level 2

    public void applyDiscount(double percentage) {
        if (percentage >= 100) { throw  new RuntimeException();}
       for(Item item : orders.values()) {
           double newPrice = item.getPrice() - (item.getPrice() * percentage/100);
           item.setPrice(newPrice);
       }
    }

    public String getMostExpensive() {
        Item item = new Item("", -Double.MAX_VALUE);
        if (orders.isEmpty()){
            throw new RuntimeException();
        }
        for (Item item2 : orders.values()) {
            if (item2.getPrice() > item.getPrice()){
                item = item2;
            }
        }
        return item.getName();
    }

    public int getItemCount() {
        return orders.size();
    }

    //level 3

    public void applyBulkDiscount(double threshold, double percentage) {
        if (orders.isEmpty()) {
            throw new RuntimeException();
        }
        for (Item item : orders.values()) {
            if (item.getPrice() > threshold) {
                double newPrice = item.getPrice() - (item.getPrice() * percentage/100);
                item.setPrice(newPrice);
            }
        }
    }

    public List<String> getItemsSortedByPrice() {
        List<Item> items = new ArrayList<>(orders.values());
        if (items.isEmpty() ) {
            throw new RuntimeException();
        }
        items.sort(Comparator.comparingDouble(Item::getPrice));
        List<String> names = new ArrayList<>();

        for (Item item : items) {
            names.add(item.getName());
        }
        return names;
    }

    //applyBulkDiscount(double threshold, double percentage) — aplica desconto só nos itens com preço acima de threshold.
    //getItemsSortedByPrice() — retorna uma lista de nomes ordenada por preço (menor pro maior).

    //Pensar em: como ordenar uma coleção de objetos por um atributo específico (dica: Comparator).

    //nivel 4

    public Map<String, Integer> groupbyPriceRange() {
        if (orders.isEmpty()) {throw new RuntimeException("There's no item on the order!");}
        Map<String, Integer> rangePrice = new HashMap<>();
        rangePrice.put("cheap", 0);
        rangePrice.put("medium", 0);
        rangePrice.put("expensive", 0);

        for (Item item : orders.values()) {
            if (item.getPrice() < 20) {
                int cheap = rangePrice.get("cheap") + 1;
                rangePrice.put("cheap", cheap);
            }
            if (item.getPrice() >= 20 && item.getPrice() <= 100) {
                int medium = rangePrice.get("medium") + 1;
                rangePrice.put("medium", medium);
            }
            if (item.getPrice() > 100) {
                int expensive = rangePrice.get("expensive") + 1;
                rangePrice.put("expensive", expensive);
            }
        }
        return rangePrice;
    }
}
