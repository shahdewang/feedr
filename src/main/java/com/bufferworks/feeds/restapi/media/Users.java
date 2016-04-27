package com.bufferworks.feeds.restapi.media;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(namespace = AbstractMediaType.API_VERSION)
@XmlType(namespace = AbstractMediaType.API_VERSION)
public class Users extends AbstractListMediaType<User> {

    public static final String MEDIA_TYPE_NAME = MEDIA_TYPE_NAME_BASE + "user.list" + SERIALIZATION_FORMAT_JSON;

    private Users() {
        //for JSON deserialization
    }

    public Users(List<User> items) {
        setItems(items);
    }
}
