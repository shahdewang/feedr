package com.bufferworks.feeds.restapi.media.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bufferworks.feeds.persist.entity.ArticleEntity;
import com.bufferworks.feeds.restapi.media.Article;
import com.bufferworks.feeds.restapi.media.Articles;
import com.bufferworks.feeds.restapi.resource.ArticlesResource;
import com.bufferworks.feeds.utils.UriBuilder;

@Component
public class ArticleConverter {

    @Autowired
    private UriBuilder uriBuilder;

    public ArticleEntity toArticleEntity(final Article article) {
        return new ArticleEntity(article.getId(), article.getTitle(), article.getContent());
    }

    public Article toArticle(final String feedname, final ArticleEntity articleEntity) {
        final Article article = new Article(articleEntity.getId(), articleEntity.getTitle(), articleEntity.getContent());
        article.setSelfUri(uriBuilder.buildRelativeUri(ArticlesResource.class, feedname));
        return article;
    }

    public Articles toArticles(final String feedname, final List<ArticleEntity> articleEntities) {
        final List<Article> articleList = new ArrayList<>();
        for (ArticleEntity entity : articleEntities) {
            articleList.add(toArticle(feedname, entity));
        }

        final Articles articles = new Articles(articleList);
        articles.setSelfUri(uriBuilder.buildRelativeUri(ArticlesResource.class, feedname));
        return articles;
    }

    public List<ArticleEntity> toArticleEntities(final List<Article> articles) {
        final List<ArticleEntity> articleList = new ArrayList<>();
        for (Article entity : articles) {
            articleList.add(toArticleEntity(entity));
        }

        return articleList;
    }
}
