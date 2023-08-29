package com.metaphore.tasks.app.web.controller;

import com.metaphore.tasks.app.service.TaskService;
import com.metaphore.tasks.app.web.model.TaskModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;
    @ApiOperation(value = "Request only one evaluation", notes = "Evaluation is registered and kept in scheduled status waiting to be executed at beginning of the day")
    @ApiResponses(value = { @ApiResponse(code = 202, message = "Accepted request and process"),
            @ApiResponse(code = 400, message = "Model Error"),
            @ApiResponse(code = 409, message = "Controled Error, in 'statusmessage' is the reason"),
            @ApiResponse(code = 500, message = "Server Error.") })
    @PostMapping(value="tasks")
    public ResponseEntity<Boolean> createTask(@RequestBody TaskModel task){
        return taskService.createTask(task);
    }
}
