package com.bufferworks.feeds.restapi.media;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(namespace = AbstractMediaType.API_VERSION)
@XmlType(namespace = AbstractMediaType.API_VERSION)
public class Feeds extends AbstractListMediaType<Feed> {

    public static final String MEDIA_TYPE_NAME = MEDIA_TYPE_NAME_BASE + "feed.list" + SERIALIZATION_FORMAT_JSON;

    private Feeds() {
        //for JSON deserialization
    }

    public Feeds(List<Feed> items) {
        setItems(items);
    }
}
