package ru.robq.blps1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ArticlePreview {
    private Long id;
    private String name;

    public ArticlePreview(Article article) {
        this(article.getId(), article.getName());
    }
}
