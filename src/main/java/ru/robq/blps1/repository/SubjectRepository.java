package ru.robq.blps1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.robq.blps1.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}