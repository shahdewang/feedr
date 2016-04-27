package com.bufferworks.feeds.restapi.media;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(namespace = AbstractMediaType.API_VERSION)
@XmlType(namespace = AbstractMediaType.API_VERSION)
public class Articles extends AbstractListMediaType<Article> {

    public static final String MEDIA_TYPE_NAME = MEDIA_TYPE_NAME_BASE + "article.list" + SERIALIZATION_FORMAT_JSON;

    private Articles() {
        //for JSON deserialization
    }

    public Articles(List<Article> items) {
        setItems(items);
    }
}
