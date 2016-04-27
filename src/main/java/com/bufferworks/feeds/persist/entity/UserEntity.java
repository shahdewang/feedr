package com.bufferworks.feeds.persist.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class UserEntity {

    @Id
    private String username;

    private List<String> subscriptions = new ArrayList<>();

    public UserEntity() {
        super();
    }

    public UserEntity(String username) {
        super();
        this.username = username;
    }

    public UserEntity(String username, List<String> subscriptions) {
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

    @Override
    public String toString() {
        return "UserEntity [username=" + username + "]";
    }
}
