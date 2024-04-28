package com.product.tm.entity;

import java.util.List;

public class Task {
    String id;
    String name;
    String parentTaskId;
    List<String> childrenTaskIdList;

    public Task() {
    }

    public Task(String id, String name,String parentTaskId, List<String> childrenTaskIdList) {
        this.id = id;
        this.name = name;
        this.parentTaskId = parentTaskId;
        this.childrenTaskIdList = childrenTaskIdList;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getParentTaskId() {
        return parentTaskId;
    }

    public List<String> getChildrenTaskIdList() {
        return childrenTaskIdList;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentTaskId(String parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    public void setChildrenTaskIdList(List<String> childrenTaskIdList) {
        this.childrenTaskIdList = childrenTaskIdList;
    }
}
