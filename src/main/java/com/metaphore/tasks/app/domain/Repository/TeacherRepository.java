package com.metaphore.tasks.app.domain.Repository;

import com.metaphore.tasks.app.domain.Entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
