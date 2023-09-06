package com.metaphore.tasks.app.web.model;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class TaskModel {
    private Long taskId;
    @NotNull
    @NotEmpty
    private String taskName;
    @NotNull
    @Future
    private Date deadline;

    @NotNull
    private Long studentId;

    @NotNull
    private Long subjectId;
    @NotNull
    private Long teacherId;

    private Boolean isFinished;
}
