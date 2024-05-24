package ru.robq.blps1.service;

import org.springframework.stereotype.Service;
import ru.robq.blps1.exception.ArticleNotFoundException;
import ru.robq.blps1.model.Article;
import ru.robq.blps1.repository.ArticleRepository;

import java.util.List;
import java.util.Random;

@Service
public class RandomArticleService {
    private List<Long> existingArticles;
    private ArticleRepository articleRepository;
    private Random random;

    public RandomArticleService(ArticleRepository articleRepository) {
        this.random = new Random();
        this.articleRepository = articleRepository;
        updateExistingArticles();
    }

    public void updateExistingArticles() {
        existingArticles = articleRepository.findAll().stream().mapToLong(Article::getId).boxed().toList();
    }

    public Long getRandomArticleId() throws ArticleNotFoundException {
        if (existingArticles == null || existingArticles.isEmpty()) {
            throw new ArticleNotFoundException("No articles found.");
        }
        return existingArticles.get(random.nextInt(existingArticles.size()));
    }
}
