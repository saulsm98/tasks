package com.metaphore.tasks.app.domain.Repository;

import com.metaphore.tasks.app.domain.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
