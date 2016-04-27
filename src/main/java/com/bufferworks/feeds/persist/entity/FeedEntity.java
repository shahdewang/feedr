package com.bufferworks.feeds.persist.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "feeds")
public class FeedEntity {

    @Id
    private String feedname;

    private List<ArticleEntity> articles = new ArrayList<>();

    public FeedEntity() {
        super();
    }

    public FeedEntity(String feedname, List<ArticleEntity> articles) {
        super();
        this.feedname = feedname;
        this.articles.addAll(articles);
    }

    public String getFeedname() {
        return feedname;
    }

    public void setFeedname(String feedname) {
        this.feedname = feedname;
    }

    public List<ArticleEntity> getArticles() {
        return Collections.unmodifiableList(articles);
    }

    public void addArticle(ArticleEntity article) {
        this.articles.add(article);
    }
}
