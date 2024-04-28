package com.product.tm.service;

import java.io.File;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.product.tm.entity.Task;
import com.product.tm.entity.TaskCard;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TmApplicationService {

    @Value("${taskcarddatabase.json}")
    private String cardFilePath;

    @Value("${taskdatabase.json}")
    private String taskFilePath;

    public ArrayList<TaskCard> getLatestTaskCardData() {
        String currentDir = System.getProperty("user.dir");
        System.out.println("Current working directory: " + currentDir);

        ObjectMapper mapper = new ObjectMapper();
        ArrayList<TaskCard> taskCardList = new ArrayList<TaskCard>();

        try {
            taskCardList = mapper.readValue(new File(cardFilePath), new TypeReference <ArrayList<TaskCard>>() {});
            System.out.println("finished reading from file");
        } catch (Exception e) {
            System.out.println("Error:"+ e.getMessage());
        }

        return taskCardList;
    }

    public ArrayList<Task> getLatestTaskData() {
        String currentDir = System.getProperty("user.dir");
        System.out.println("Current working directory: " + currentDir);

        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Task> taskList = new ArrayList<Task>();

        try {
            taskList = mapper.readValue(new File(taskFilePath), new TypeReference <ArrayList<Task>>() {});
            System.out.println("finished reading from file");
        } catch (Exception e) {
            System.out.println("Error:"+ e.getMessage());
        }

        return taskList;
    }

    public void updateTaskCardData(ArrayList<TaskCard> taskCardList) {
        System.out.println("Update data called");

        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File(cardFilePath), taskCardList);
            System.out.println("finished writing to file");
        } catch (Exception e) {
            System.out.println("Error:"+ e.getMessage());
        }
    }

    public void updateTaskData(ArrayList<Task> taskList) {
        System.out.println("Update data called");

        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File(taskFilePath), taskList);
            System.out.println("finished writing to file");
        } catch (Exception e) {
            System.out.println("Error:"+ e.getMessage());
        }
    }
    
}
