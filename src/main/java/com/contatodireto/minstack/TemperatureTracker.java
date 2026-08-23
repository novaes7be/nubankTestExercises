package com.contatodireto.minstack;

import java.util.Comparator;
import java.util.Stack;

public class TemperatureTracker {

    Stack<Double> temperatureStack = new Stack<>();

    public void record(double value) {
        temperatureStack.add(value);
    }

    public void remove_last(){
        if (temperatureStack.isEmpty()) {
            throw new RuntimeException("There's no temperature to be removed");
        }
        temperatureStack.pop();
    }

    public double get_max() {
        if (temperatureStack.isEmpty()) {
            throw new RuntimeException();
        } else {
            double maior = temperatureStack.get(0);
            for(Double c : temperatureStack) {

                if (c > maior){
                    maior = c;
                }
            }
            return maior;
        }
    }

    public int get_count() {
        return temperatureStack.size();
    }

}
