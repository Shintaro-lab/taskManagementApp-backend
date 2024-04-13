package com.product.tm.control;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.tm.entity.TaskCard;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/tm")
@RestController
public class TmApplicationController {

    @Autowired
    private com.product.tm.service.TmApplicationService tmApplicationService;

    @RequestMapping("getLatestData")
    @CrossOrigin
    public ArrayList<TaskCard> searchEmp() {
        return tmApplicationService.getLatestData();
    }

    @PostMapping("updateData")
    @CrossOrigin
    public void postMethodName(@RequestBody ArrayList<TaskCard> taskCardList) {
        tmApplicationService.updateData(taskCardList);
    }
    
}
