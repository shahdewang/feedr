package com.bufferworks.feeds.restapi.media;

/**
 * Resource media for Article
 */
public class Article extends AbstractMediaType {

    public static final String MEDIA_TYPE_NAME = MEDIA_TYPE_NAME_BASE + "articles" + SERIALIZATION_FORMAT_JSON;

    private Integer id;

    private String title;

    private String content;

    @SuppressWarnings("unused")
    private Article() {
        super();
    }

    public Article(Integer id, String title, String content) {
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
