package com.metaphore.tasks.app.web.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TaskModel {
    private Long taskId;
    private String taskName;
    private Date deadline;
    private Long studentId;
    private Long subjectId;
    private Long teacherId;
}
