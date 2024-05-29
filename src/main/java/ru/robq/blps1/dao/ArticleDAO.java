package ru.robq.blps1.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.robq.blps1.model.Article;

@Getter
@Setter
@AllArgsConstructor
public class ArticleDAO {
    private Long id;
    private String name;

    public ArticleDAO(Article article) {
        this(article.getId(), article.getName());
    }
}
