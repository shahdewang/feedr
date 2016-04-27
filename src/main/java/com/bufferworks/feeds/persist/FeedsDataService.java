package com.bufferworks.feeds.persist;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.bufferworks.feeds.persist.entity.FeedEntity;

public interface FeedsDataService extends MongoRepository<FeedEntity, String> {

    /**
     * Returns all Feeds without the articles.
     */
    @Query(value = "{ }", fields = "{ 'articles': 0 }")
    List<FeedEntity> findAllFeeds();

    List<FeedEntity> findByFeednameIn(List<String> feednames);
}
