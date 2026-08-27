package com.contatodireto.minstack;

import java.util.*;

public class BookingSystem {

    Map<String, Integer> booking = new HashMap<>();

    public void book_room(String nome, int guests) {
        int defaultBooking =  booking.getOrDefault(nome, 0);
        booking.put(nome, defaultBooking + guests);
    }

    public boolean cancel_booking(String room, int guests) {
        int bookValue =  booking.getOrDefault(room, 0);
        int sub = bookValue - guests;

        if (booking.containsKey(room) && sub >= 0) {
            booking.put(room, sub);
            return true;
        } else {
            return false;
        }
    }

    public int get_guests(String room) {
        if (!booking.containsKey(room)) {
            throw new RuntimeException();
        }
        return booking.get(room);
    }

    public Integer get_total_guests() {
        int sum = 0;
        for (int guest : booking.values()) {
            sum += guest;
        }
        return sum;
    }

    public String get_most_booked_room() {
        if (booking.isEmpty()) {
            throw new RuntimeException();
        } else {

            int bestValue = Integer.MIN_VALUE;
            for (int anyValue : booking.values()) {
                if (anyValue > bestValue) {
                    bestValue = anyValue;
                }
            }
            String best = null;
            for (String s : booking.keySet()) {
                if (booking.get(s) == bestValue) {
                    if (best == null || s.compareTo(best) < 0) {
                        best = s;
                    }
                }
            }
            return best;
        }
    }

    public Integer count_empty_rooms() {
        int count = 0;
        for (int anyValue : booking.values()) {
            if (anyValue == 0) {
                count++;
            }
        }
        return count;
    }

    public List<String> get_rooms_sorted_by_guests() {
        List<String> sorted = new ArrayList<>(booking.keySet());
        sorted.sort(Comparator.comparingInt(name -> booking.get(name)));
        return sorted;
    }

    public List<String> get_rooms_above(int threshold) {
        List<String> aboveThreshold = new ArrayList<>();
        for (String anyValue : booking.keySet()) {
            if (booking.get(anyValue) > threshold) {
                aboveThreshold.add(anyValue);
            }
        }
        return aboveThreshold;
    }
    public Integer cancel_all_above(int threshold) {
        int count = 0;
        for (String anyString : booking.keySet()) {
            if (booking.get(anyString) > threshold) {
                booking.put(anyString, 0);
                count++;
            }
        }
        return count;
    }

    public List<String> get_top_n_rooms(int n) {
        List<String> sorted = new ArrayList<>(booking.keySet());
        List<String> sortedResult = new ArrayList<>();
        int count = 0;
        sorted.sort(Comparator.comparingInt(name -> booking.get(name)));
        sorted.sort(Collections.reverseOrder());
        if (booking.size() < n) {
            return sorted;
        } else {
            for (String anyValue : sorted) {
                sortedResult.add(anyValue);
                count++;
                if (count == n) {
                    break;
                }
            }
        }
        return sortedResult;
    }

    public boolean merge_rooms(String from, String to) {
        if (!booking.containsKey(from)) {
            return false;
        }
        int toValue = booking.getOrDefault(to, 0);
        int toValueAddition = 0;
        toValueAddition = booking.get(from);
        booking.put(to, toValue + toValueAddition);
        booking.remove(from);
        return true;
    }
}
