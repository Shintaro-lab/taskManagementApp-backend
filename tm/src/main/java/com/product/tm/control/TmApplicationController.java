package com.product.tm.control;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.tm.entity.Task;
import com.product.tm.entity.TaskCard;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/tm")
@RestController
public class TmApplicationController {

    @Autowired
    private com.product.tm.service.TmApplicationService tmApplicationService;

    @RequestMapping("getLatestTaskCardData")
    @CrossOrigin
    public ArrayList<TaskCard> getLatestTaskCardData() {
        return tmApplicationService.getLatestTaskCardData();
    }

    @RequestMapping("getLatestTaskData")
    @CrossOrigin
    public ArrayList<Task> getLatestTaskData() {
        return tmApplicationService.getLatestTaskData();
    }

    @PostMapping("updateTaskCardData")
    @CrossOrigin
    public void updateTaskCardData(@RequestBody ArrayList<TaskCard> taskCardList) {
        tmApplicationService.updateTaskCardData(taskCardList);
    }

    @PostMapping("updateTaskData")
    @CrossOrigin
    public void updateTaskData(@RequestBody ArrayList<Task> taskList) {
        tmApplicationService.updateTaskData(taskList);
    }

}
