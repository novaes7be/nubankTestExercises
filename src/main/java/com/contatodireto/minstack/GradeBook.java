package com.contatodireto.minstack;

import java.util.*;

public class GradeBook {

    Map<String,Student> studentMap = new HashMap<>();

    public GradeBook() {
    }

    public void addStudent(String name, double grade) {
        Student student = new Student(name, grade);
        if (studentMap.containsKey(name)) {
            student.setGrade(grade);
        }
      studentMap.put(name, student);
    }

    public void removeBelow(double threshold) {
        studentMap.values().removeIf(s -> s.getGrade() < threshold);
    }

    public double getGrade(String name) {
        Student student = studentMap.get(name);
        if (student == null) {
            throw new RuntimeException();
        }
        return student.getGrade();
    }

    public double getAverage() {
        if (studentMap.isEmpty()) {
            throw new RuntimeException();
        }
        double sum = 0;
        for (Student s : studentMap.values()) {
            sum += s.getGrade();
        }
        return sum / studentMap.size();
    }

    public String getTopStudent() {
        Student highscore = new Student("", -Double.MAX_VALUE);
        if (studentMap.isEmpty()) {
            throw new RuntimeException();
        } else {


            for (Student student : studentMap.values()) {
                if (student.getGrade() > highscore.getGrade()) {
                    highscore = student;
                }

            }
        }
        return highscore.getName();
    }

}
