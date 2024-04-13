package com.product.tm.service;

import java.io.File;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.product.tm.entity.TaskCard;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TmApplicationService {

    @Value("${database.json}")
    private String filePath;

    public ArrayList<TaskCard> getLatestData() {
        String currentDir = System.getProperty("user.dir");
        System.out.println("Current working directory: " + currentDir);

        ObjectMapper mapper = new ObjectMapper();
        ArrayList<TaskCard> taskCardList = new ArrayList<TaskCard>();

        try {
            taskCardList = mapper.readValue(new File(filePath), new TypeReference <ArrayList<TaskCard>>() {});
            System.out.println("finished reading from file");
        } catch (Exception e) {
            System.out.println("Error:"+ e.getMessage());
        }

        return taskCardList;
    }

    public void updateData(ArrayList<TaskCard> taskCardList) {
        System.out.println("Update data called");

        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File(filePath), taskCardList);
            System.out.println("finished writing to file");
        } catch (Exception e) {
            System.out.println("Error:"+ e.getMessage());
        }
    }
    
}
