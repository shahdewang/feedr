package com.bufferworks.feeds.persist.entity;

import org.springframework.data.annotation.Id;

public class UserArticleEntity {

    @Id
    private String feedname;

    private ArticleEntity articles;

    @SuppressWarnings("unused")
    private UserArticleEntity() {
        super();
    }

    public UserArticleEntity(String feedname, ArticleEntity articles) {
        super();
        this.feedname = feedname;
        this.articles = articles;
    }

    public String getFeedname() {
        return feedname;
    }

    public void setFeedname(String feedname) {
        this.feedname = feedname;
    }

    public ArticleEntity getArticles() {
        return articles;
    }

    public void setArticle(ArticleEntity articles) {
        this.articles = articles;
    }
}
