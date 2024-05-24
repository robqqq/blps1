package ru.robq.blps1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ru.robq.blps1.model.Article;

import java.util.List;

@Component
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByCategoryId(Long categoryId);
    List<Article> findBySubjectId(Long subjectId);
}