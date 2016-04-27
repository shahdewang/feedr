package com.bufferworks.feeds.restapi.media;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(namespace = AbstractMediaType.API_VERSION)
@XmlType(namespace = AbstractMediaType.API_VERSION)
public class UserArticles extends AbstractListMediaType<UserArticle> {

    public static final String MEDIA_TYPE_NAME = MEDIA_TYPE_NAME_BASE + "userarticles.list" + SERIALIZATION_FORMAT_JSON;

    private UserArticles() {
        //for JSON deserialization
    }

    public UserArticles(List<UserArticle> items) {
        setItems(items);
    }
}
