package com.bufferworks.feeds.restapi.media;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Resource media for Feed.
 */
public class Feed extends AbstractMediaType {

    public static final String MEDIA_TYPE_NAME = MEDIA_TYPE_NAME_BASE + "feeds" + SERIALIZATION_FORMAT_JSON;

    private String feedname;

    private List<Article> articles = new ArrayList<>();

    @SuppressWarnings("unused")
    private Feed() {
        super();
    }

    public Feed(String feedname) {
        this.feedname = feedname;
    }

    public Feed(String feedname, List<Article> articles) {
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

    public List<Article> getArticles() {
        return Collections.unmodifiableList(articles);
    }

    public void addArticle(Article article) {
        this.articles.add(article);
    }
}
