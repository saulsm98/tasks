package com.metaphore.tasks.app.service;

import com.metaphore.tasks.app.web.model.TaskModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class TaskService {

    public ResponseEntity<Boolean> createTask(TaskModel task){
        ResponseEntity<Boolean> response = new ResponseEntity<>(HttpStatus.OK);
        try{

        }catch (Exception e){
            log.info(e);

        }
        return response;
    }
}
