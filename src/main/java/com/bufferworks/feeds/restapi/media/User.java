package com.bufferworks.feeds.restapi.media;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Resource media for User.
 */
public class User extends AbstractMediaType {

    public static final String MEDIA_TYPE_NAME = MEDIA_TYPE_NAME_BASE + "user" + SERIALIZATION_FORMAT_JSON;

    private String username;

    private List<String> subscriptions = new ArrayList<>();

    @SuppressWarnings("unused")
    private User() {
        super();
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, List<String> subscriptions) {
        super();
        this.username = username;
        this.subscriptions.addAll(subscriptions);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getSubscriptions() {
        return Collections.unmodifiableList(subscriptions);
    }

    public void addSubscription(String subscription) {
        this.subscriptions.add(subscription);
    }
}
