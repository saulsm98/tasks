package com.metaphore.tasks.app.web.controller;

import com.metaphore.tasks.app.service.TaskService;
import com.metaphore.tasks.app.web.model.TaskModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {
    @Autowired
    private TaskService taskService;
    @ApiOperation(value = "Request only one evaluation", notes = "Evaluation is registered and kept in scheduled status waiting to be executed at beginning of the day")
    @ApiResponses(value = { @ApiResponse(code = 202, message = "Accepted request and process"),
            @ApiResponse(code = 400, message = "Model Error"),
            @ApiResponse(code = 409, message = "Controled Error, in 'statusmessage' is the reason"),
            @ApiResponse(code = 500, message = "Server Error.") })
    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody TaskModel task){
        return taskService.createTask(task);
    }

    @GetMapping
    public ResponseEntity<?> consultTask(@RequestBody TaskModel task){
        return taskService.consultTask(task);
    }

    @PatchMapping
    public ResponseEntity<?> updateTask(@RequestBody TaskModel task){
        return taskService.updateTask(task);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTask(@RequestBody TaskModel task){
        return taskService.deleteTask(task);
    }
}
