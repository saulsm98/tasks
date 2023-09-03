package com.metaphore.tasks.app.facade;

import com.metaphore.tasks.app.domain.Entity.Task;
import com.metaphore.tasks.app.domain.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Transactional
public class TaskFacade {
    @Autowired
    private TaskRepository repository;

    public Task save(Task task){
        return repository.save(task);
    }

    public Optional<Task> findById(Long taskId) { return repository.findById(taskId);}

    public void deleteById(Long taskId){ repository.deleteById(taskId);}
}
