package com.bufferworks.feeds.restapi.media;

/**
 * Resource media for UserArticle
 */
public class UserArticle extends AbstractMediaType {

    private String feedname;

    private Article article;

    @SuppressWarnings("unused")
    private UserArticle() {
        super();
    }

    public UserArticle(String feedname, Article article) {
        super();
        this.feedname = feedname;
        this.article = article;
    }

    public String getFeedname() {
        return feedname;
    }

    public void setFeedname(String feedname) {
        this.feedname = feedname;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
