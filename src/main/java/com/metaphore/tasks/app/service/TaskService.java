package com.metaphore.tasks.app.service;

import com.metaphore.tasks.app.domain.Entity.Task;
import com.metaphore.tasks.app.facade.TaskFacade;
import com.metaphore.tasks.app.web.model.TaskModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
@Log4j2
public class TaskService {
    @Autowired
    private TaskFacade taskFacade;

    /**
     * Method that create a new task
     * @param task
     * @return TaskModel created
     */
    public ResponseEntity<TaskModel> createTask(TaskModel task){
        try{
            Task taskEntity = this.taskFacade.save(new Task(task.getTaskName(),task.getDeadline(),task.getStudentId(), task.getSubjectId(), task.getTeacherId()));
            TaskModel createdTask = this.TaskEntityToTaskModel(taskEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
        }catch (Exception e){
            log.info(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Method that consult a task
     * @param task
     * @return TaskModel updated
     */
    public ResponseEntity<TaskModel> consultTask(TaskModel task){
        try{
            Optional<Task> taskEntity = this.taskFacade.findById(task.getTaskId());
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
     * @param task
     * @return updatedTask
     */
    public ResponseEntity<TaskModel> updateTask(TaskModel task){
        try{
            Optional<Task> taskEntity = this.taskFacade.findById(task.getTaskId());
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
     * @param task
     * @return
     */
    public ResponseEntity<TaskModel> deleteTask(TaskModel task){
        try{
            this.taskFacade.deleteById(task.getTaskId());
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
        if(!ObjectUtils.isEmpty(updates.getTaskName()))
            task.setNameTask(updates.getTaskName());
        if(!ObjectUtils.isEmpty(updates.getDeadline()))
            task.setDeadline(updates.getDeadline());
        if(!ObjectUtils.isEmpty(updates.getIsFinished()))
            task.setFinish(updates.getIsFinished());
        return task;
    }
}
