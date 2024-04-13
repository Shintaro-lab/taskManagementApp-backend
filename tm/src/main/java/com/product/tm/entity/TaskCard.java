package com.product.tm.entity;

import java.util.List;

public class TaskCard {
    String id;
    String title;
    List<Task> taskList;

    public TaskCard() {
    }

    public TaskCard(String id, String title, List<Task> taskList) {
        this.id = id;
        this.title = title;
        this.taskList = taskList;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
