package com.metaphore.tasks.app.service;

import com.metaphore.tasks.app.domain.Entity.Task;
import com.metaphore.tasks.app.facade.TaskFacade;
import com.metaphore.tasks.app.web.model.TaskModel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Log4j2
public class TaskService {
    @Autowired
    private TaskFacade taskFacade;

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    /**
     * Method that create a new task
     * @param task
     * @return TaskModel created
     */
    public ResponseEntity<?> createTask(TaskModel task){
        try {
            Set<ConstraintViolation<TaskModel>> violations = validator.validate(task);
            if (!violations.isEmpty()){
                List<String> errors = violations.stream().map(errorC -> errorC.getMessage()).collect(Collectors.toList());
                return ResponseEntity.status(HttpStatus.CONFLICT).body(errors);
            }
            Task taskEntity = this.taskFacade.save(new Task(task.getTaskName(), task.getDeadline(), task.getStudentId(), task.getSubjectId(), task.getTeacherId()));
            TaskModel createdTask = this.TaskEntityToTaskModel(taskEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
        }catch (Exception e){
            log.info(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Method that consult a task
     * @param taskId
     * @return TaskModel updated
     */
    public ResponseEntity<TaskModel> consultTask(Long taskId){
        try{
            Optional<Task> taskEntity = this.taskFacade.findById(taskId);
            if(taskEntity.isPresent()){
                TaskModel consultedTask =  this.TaskEntityToTaskModel(taskEntity.get());
                return ResponseEntity.status(HttpStatus.OK).body(consultedTask);
            }else{
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }catch (Exception e){
            log.info(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Method to updated a task
     * @param taskId
     * @param task
     * @return updatedTask
     */
    public ResponseEntity<TaskModel> updateTask(Long taskId, TaskModel task){
        try{
            Optional<Task> taskEntity = this.taskFacade.findById(taskId);
            if(taskEntity.isPresent()){
                Task preparedTask = this.setUpdatedSettingTask(taskEntity.get(), task);
                Task updatedTask =  this.taskFacade.save(preparedTask);
                return ResponseEntity.status(HttpStatus.OK).body(this.TaskEntityToTaskModel(updatedTask));
            }else{
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }catch (Exception e){
            log.info(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Method to delete a task
     * @param taskId
     * @return
     */
    public ResponseEntity<TaskModel> deleteTask(Long taskId){
        try{
            this.taskFacade.deleteById(taskId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            log.info(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Map a TaskEntity to TaskModel
     * @param task
     * @return
     */
    private TaskModel TaskEntityToTaskModel(Task task){
        return  new TaskModel(task.getId(), task.getNameTask(), task.getDeadline(),task.getStudent().getId(),task.getSubject().getId(),task.getTeacher().getId(),task.getFinish());
    }

    /**
     * Only set modifable fields
     * @param task
     * @param updates
     * @return Modified Task
     */
    private Task setUpdatedSettingTask(Task task,TaskModel updates){
        Set<ConstraintViolation<TaskModel>> violations = validator.validate(updates);
        if(!ObjectUtils.isEmpty(updates.getTaskName()))
            task.setNameTask(updates.getTaskName());
        if(!ObjectUtils.isEmpty(updates.getDeadline()))
            task.setDeadline(updates.getDeadline());
        if(!ObjectUtils.isEmpty(updates.getIsFinished()))
            task.setFinish(updates.getIsFinished());
        return task;
    }
}
