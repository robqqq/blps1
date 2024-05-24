package ru.robq.blps1.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.robq.blps1.exception.ArticleNotFoundException;
import ru.robq.blps1.model.ArticlePreview;
import ru.robq.blps1.model.Category;
import ru.robq.blps1.model.Subject;
import ru.robq.blps1.repository.ArticleRepository;
import ru.robq.blps1.repository.CategoryRepository;
import ru.robq.blps1.repository.SubjectRepository;
import ru.robq.blps1.service.RandomArticleService;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class ArticleController {
    private ArticleRepository articleRepository;
    private CategoryRepository categoryRepository;
    private SubjectRepository subjectRepository;
    private RandomArticleService randomArticleService;

    @GetMapping("/categories")
    public List<Category> categories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/subjects")
    public List<Subject> subjects() {
        return subjectRepository.findAll();
    }

    @GetMapping("/articles/{id}")
    public String articles(@PathVariable Long id) throws ArticleNotFoundException {
        return articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException("Article not found.")).getHtml();
    }

    @GetMapping("/articles/category/{categoryId}")
    public List<ArticlePreview> articlesByCategory(@PathVariable Long categoryId){
        return articleRepository.findByCategoryId(categoryId).stream().map(article -> new ArticlePreview(article)).toList();
    }

    @GetMapping("/articles/subject/{subjectId}")
    public List<ArticlePreview> articlesBySubject(@PathVariable Long subjectId){
        return articleRepository.findBySubjectId(subjectId).stream().map(article -> new ArticlePreview(article)).toList();
    }

    @GetMapping("/articles/random")
    public String randomArticle(HttpServletResponse response) throws IOException, ArticleNotFoundException {
        return articles(randomArticleService.getRandomArticleId());
    }
}
