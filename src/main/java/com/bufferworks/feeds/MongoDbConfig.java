package com.bufferworks.feeds;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.stereotype.Component;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Component
public class MongoDbConfig extends AbstractMongoConfiguration {

    @Value("#{systemProperties['mongodb.host'] ?: 'localhost'}")
    private String mongoHost;

    @Value("#{systemProperties['mongodb.port'] ?: 27017}")
    private String mongoPort;

    @Value("#{systemProperties['mongodb.database'] ?: 'feedsportal'}")
    private String mongoDB;

    @Override
    public String getDatabaseName() {
        return mongoDB; //"feedsportal";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(mongoHost, Integer.valueOf(mongoPort));
    }
}
