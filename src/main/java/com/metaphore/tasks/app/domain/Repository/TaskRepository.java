package com.metaphore.tasks.app.domain.Repository;

import com.metaphore.tasks.app.domain.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
