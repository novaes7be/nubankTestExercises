package com.contatodireto.minstack;

import java.util.*;

public class StudentGrades {

    private Map<String, List<Double>> grades = new HashMap<>();

    public void addStudent(String name) {
        grades.put(name, new ArrayList<>());
    }

    public void addGrade(String name, double grade) {
        grades.get(name).add(grade);
    }


    public double getAverage(String name) {
        double sum = 0;

        for (double i : grades.get(name)) {
            sum += i;
        }
        return sum / grades.get(name).size();
    }

    public List<String> getStudents() {
        return grades.keySet().stream().toList();
    }

    public int getTotalGrades() {
        int sum = 0;

        for (String i : grades.keySet()) {
            sum += grades.get(i).size();
        }
        return sum;
    }

    public List<String> getStudentsWithAverageAbove(double minAverage) {
        List<String> namesOfStudents = new ArrayList<>();
        for (String name : grades.keySet()) {
            if (getAverage(name) > minAverage) {
                 namesOfStudents.add(name);
            }
        }
        return namesOfStudents;
    }
}