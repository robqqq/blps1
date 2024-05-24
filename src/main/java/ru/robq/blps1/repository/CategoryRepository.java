package ru.robq.blps1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ru.robq.blps1.model.Category;

@Component
public interface CategoryRepository extends JpaRepository<Category, Long> {
}