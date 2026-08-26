package com.contatodireto.minstack;

import java.util.*;

public class TaskQueue {
    Set<String> processedTasks = new HashSet<>();
    Deque<String> pendingTasks = new ArrayDeque<>();
    Map<String, Integer> priorities = new HashMap<>();

    public void addTask(String name) {
        if (!processedTasks.contains(name)) {
            pendingTasks.add(name);
        }
    }
    public String processNext() {
        if (pendingTasks.isEmpty()) {
            throw new RuntimeException();
        }
        String task = pendingTasks.removeFirst();
        processedTasks.add(task);
        return task;
    }
    public boolean isProcessed(String name) {
        return processedTasks.contains(name);
    }

    public int getPendingCount() {
        return pendingTasks.size();
    }

    public void addTaskWithPriority(String name, int priority) {
        if (!processedTasks.contains(name)) {
            pendingTasks.add(name);
            priorities.put(name, priority);
        }
    }

    public String processedNext() {
        if (pendingTasks.isEmpty()) {
            throw new RuntimeException();
        }
        String task = getHighestPriorityTask();
        pendingTasks.remove(task);
        priorities.remove(task);
        processedTasks.remove(task);
        return task;
    }

    public String getHighestPriorityTask() {
        if (pendingTasks.isEmpty()) {
            throw new RuntimeException();
        } else {
            String melhor = null;
            for (String task : pendingTasks) {
                if (melhor == null || priorities.get(task) < priorities.get(melhor)) {
                    melhor = task;
                }
            }
            return melhor;
        }
    }
    public boolean removeTask(String name) {
        if (!priorities.containsKey(name)) {
            return false;
        } else {
            priorities.remove(name);
            pendingTasks.remove(name);
            return true;
        }
    }

    public String getOldestLowPriorityTask() {
        if (pendingTasks.isEmpty()) {
            throw new RuntimeException();
        }
        for (String task : pendingTasks) {
            if (priorities.get(task) >= 5) {
                return task;
            }
        }
        throw new RuntimeException();
    }

    public int countTasksAbovePriority(int threshold) {
        int quantity = 0;
        for (String task : pendingTasks) {
            if (priorities.get(task) > threshold) {
                quantity++;
            }
        }
        return quantity;
    }

    public List<String> getPendingTaskNames(int minPriority) {

        List<String> result = new ArrayList<>();

        for (String task : pendingTasks) {
            if (priorities.get(task) >= minPriority) {
                result.add(task);
            }
        }
        return result;
    }
}
