package com.todolist.model;

public class Task {
    private int id;
    private String textTask;
    private boolean textStatus;

    public Task(String textTask) {
        this.textTask = textTask;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextTask() {
        return textTask;
    }

    public void setTextTask(String textTask) {
        this.textTask = textTask;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", textTask='" + textTask + '\'' +
                '}';
    }
}

