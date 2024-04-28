package com.product.tm.entity;

import java.util.List;

public class TaskCard {
    String id;
    String title;
    List<String> taskIdList;

    public TaskCard() {
    }

    public TaskCard(String id, String title, List<String> taskIdList) {
        this.id = id;
        this.title = title;
        this.taskIdList = taskIdList;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getTaskIdList() {
        return taskIdList;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTaskList(List<String> taskIdList) {
        this.taskIdList = taskIdList;
    }
}
