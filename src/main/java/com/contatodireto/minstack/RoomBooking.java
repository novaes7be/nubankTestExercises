package com.contatodireto.minstack;

import java.util.HashSet;
import java.util.Set;

public class RoomBooking {
    Set<Integer> slotBooking = new HashSet<>();

    public void book(int slot) {
        if(slotBooking.contains(slot)) {
            throw new RuntimeException();
        } else {
            slotBooking.add(slot);
        }
    }
    public boolean cancel(int slot) {
        if (slotBooking.contains(slot)) {
            slotBooking.remove(slot);
            return true;
        } else {
            return false;
        }
    }
    public boolean is_booked(int slot) {
        return slotBooking.contains(slot);
    }

    public int get_booked_count() {
        return slotBooking.size();
    }

    public void book_range(int start, int end) {
        for (int i = start; i <= end; i++) {
            if (slotBooking.contains(i)) {
                throw new RuntimeException();
            }
        }
        while (start <= end) {
            slotBooking.add(start);
            start++;
        }
    }

    public int get_total_booked_in_range(int start, int end) {
        int count = 0;
        for (int booking : slotBooking) {
            if ( booking >= start && booking <= end){
                ++count;
            }
        }
        return count;
    }

    public int cancel_all() {
        int count = slotBooking.size();
        slotBooking.clear();
        return count;
    }
}
