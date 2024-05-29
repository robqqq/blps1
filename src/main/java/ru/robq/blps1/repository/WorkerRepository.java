package ru.robq.blps1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.robq.blps1.model.Worker;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
    List<Worker> findAllByIsBusy(boolean isBusy);
}
