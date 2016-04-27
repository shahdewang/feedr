package com.bufferworks.feeds.persist.entity;

import org.springframework.data.annotation.Id;

public class ArticleEntity {

    @Id
    private Integer id;

    private String title;

    private String content;

    public ArticleEntity() {
        super();
    }

    public ArticleEntity(Integer id, String title, String content) {
        super();
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
