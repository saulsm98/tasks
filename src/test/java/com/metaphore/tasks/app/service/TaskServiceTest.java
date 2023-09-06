package com.metaphore.tasks.app.service;

import com.metaphore.tasks.app.domain.Entity.Student;
import com.metaphore.tasks.app.domain.Entity.Subject;
import com.metaphore.tasks.app.domain.Entity.Task;
import com.metaphore.tasks.app.domain.Entity.Teacher;
import com.metaphore.tasks.app.facade.TaskFacade;
import com.metaphore.tasks.app.util.Constants;
import com.metaphore.tasks.app.web.model.TaskModel;
import lombok.extern.log4j.Log4j2;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.Assert;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;


import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@Log4j2
public class TaskServiceTest {
    @Mock
    TaskFacade taskFacade;

    @InjectMocks
    TaskService taskService;

    TaskModel taskModel =  TaskModel.builder().taskName("Prueba").deadline(new Date(System.currentTimeMillis() + Constants.MILI_DAY)).studentId(1L).subjectId(1L).teacherId(1L).build();
    Task taskEntiy = Task.builder().nameTask("Prueba").deadline(new Date(System.currentTimeMillis() + Constants.MILI_DAY)).student(new Student(1L)).subject(new Subject(1L)).teacher(new Teacher(1L)).id(1L).finish(false).build();
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
     void createTask(){
        init();
        when(taskFacade.save(any())).thenReturn(this.taskEntiy);
        Assert.assertEquals(HttpStatusCode.valueOf(HttpStatus.CREATED.value()), taskService.createTask(this.taskModel).getStatusCode());
        TaskModel errorTask = this.taskModel;
        errorTask.setDeadline(new Date(System.currentTimeMillis() - Constants.MILI_DAY));
        Assert.assertEquals(HttpStatusCode.valueOf(HttpStatus.CONFLICT.value()), taskService.createTask(this.taskModel).getStatusCode());
     }

     @Test
     void consultTask(){
        init();
        Optional<Task> taskconsult = Optional.of(this.taskEntiy);
        when(taskFacade.findById(any())).thenReturn(taskconsult);
        Assert.assertEquals(HttpStatusCode.valueOf(HttpStatus.OK.value()), taskService.consultTask(1L).getStatusCode());
        when(taskFacade.findById(any())).thenReturn(Optional.empty());
        Assert.assertEquals(HttpStatusCode.valueOf(HttpStatus.NO_CONTENT.value()), taskService.consultTask(1L).getStatusCode());
     }
    @Test
    void updateTask(){
        init();
        Optional<Task> taskconsult = Optional.of(this.taskEntiy);
        when(taskFacade.findById(any())).thenReturn(taskconsult);
        when(taskFacade.save(any())).thenReturn( this.taskEntiy);
        Assert.assertEquals(HttpStatusCode.valueOf(HttpStatus.OK.value()), taskService.consultTask(1L).getStatusCode());
        when(taskFacade.findById(any())).thenReturn(Optional.empty());
        Assert.assertEquals(HttpStatusCode.valueOf(HttpStatus.NO_CONTENT.value()), taskService.consultTask(1L).getStatusCode());
    }

    @Test
    void deleteTask(){
        init();
        doNothing().when(taskFacade).deleteById(any());
        Assert.assertEquals(HttpStatusCode.valueOf(HttpStatus.NO_CONTENT.value()), taskService.consultTask(1L).getStatusCode());
    }

}
