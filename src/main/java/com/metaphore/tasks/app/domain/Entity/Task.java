package com.metaphore.tasks.app.domain.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
@Table(name = "tasks")
@Entity
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(name = "name_task")
    private String nameTask;
    @Column
    private Date deadline;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id", referencedColumnName = "id", nullable = false)
    private Subject subject;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id", nullable = false)
    private Teacher teacher;

    @Column
    private Boolean finish;

    public Task(String nameTask, Date deadline, Long studentId, Long subjectId, Long teacherId){
        this.nameTask = nameTask;
        this.deadline = deadline;
        this.student = new Student(studentId);
        this.subject = new Subject(subjectId);
        this.teacher = new Teacher(teacherId);
        this.finish = false;
    }
}
