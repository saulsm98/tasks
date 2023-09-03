package com.metaphore.tasks.app.domain.Repository;

import com.metaphore.tasks.app.domain.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
