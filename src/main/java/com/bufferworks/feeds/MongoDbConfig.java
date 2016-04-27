package com.bufferworks.feeds;

import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.stereotype.Component;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Component
public class MongoDbConfig extends AbstractMongoConfiguration {

    @Override
    public String getDatabaseName() {
        return "feedsportal";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient("localhost", 27017);
    }
}
