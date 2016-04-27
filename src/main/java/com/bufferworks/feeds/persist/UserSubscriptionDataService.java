package com.bufferworks.feeds.persist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.bufferworks.feeds.persist.entity.UserEntity;

@Component
public class UserSubscriptionDataService {

    @Autowired
    protected MongoTemplate mongoTemplate;

    public void subscribe(final String username, final String feed) {
        mongoTemplate.updateFirst(Query.query(Criteria.where("username").is(username)),
                new Update().addToSet("subscriptions", feed), UserEntity.class);
    }

    public void unsubscribe(final String username, final String feed) {
        mongoTemplate.updateFirst(Query.query(Criteria.where("username").is(username)),
                new Update().pull("subscriptions", feed), UserEntity.class);
    }
}
