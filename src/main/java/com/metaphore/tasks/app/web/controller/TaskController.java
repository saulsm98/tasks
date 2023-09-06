package com.metaphore.tasks.app.web.controller;

import com.metaphore.tasks.app.service.TaskService;
import com.metaphore.tasks.app.web.model.TaskModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;
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
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Created the task"),
    @ApiResponse(code = 409, message = "Model Error"),
    @ApiResponse(code = 403, message = "Authentication Failded"),
    @ApiResponse(code = 500, message = "Server Error.") })
    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody TaskModel task){
        return taskService.createTask(task);
    }

    @ApiOperation(value = "Request only one evaluation", notes = "Evaluation is registered and kept in scheduled status waiting to be executed at beginning of the day")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
    @ApiResponse(code = 500, message = "Server Error.") })
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> consultTask(@PathVariable Long taskId){
        return taskService.consultTask(taskId);
    }

    @ApiOperation(value = "Request only one evaluation", notes = "Evaluation is registered and kept in scheduled status waiting to be executed at beginning of the day")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
    @ApiResponse(code = 204, message = "No Content (Don't found the task)"),
    @ApiResponse(code = 500, message = "Server Error.") })
    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long taskId, @RequestBody TaskModel task){
        return taskService.updateTask(taskId, task);
    }

    @ApiOperation(value = "Request only one evaluation", notes = "Evaluation is registered and kept in scheduled status waiting to be executed at beginning of the day")
    @ApiResponses(value = { @ApiResponse(code = 204, message = "No Content"),
    @ApiResponse(code = 500, message = "Server Error.") })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId){
        return taskService.deleteTask(taskId);
    }
}
