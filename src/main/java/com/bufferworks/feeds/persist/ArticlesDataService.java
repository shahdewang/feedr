package com.bufferworks.feeds.persist;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.bufferworks.feeds.persist.entity.ArticleEntity;
import com.bufferworks.feeds.persist.entity.FeedEntity;
import com.bufferworks.feeds.persist.entity.UserArticleEntity;

@Component
public class ArticlesDataService {

    @Autowired
    protected MongoTemplate mongoTemplate;

    public void addArticle(final String feedname, final ArticleEntity article) {
        mongoTemplate.updateFirst(Query.query(Criteria.where("feedname").is(feedname)),
                new Update().pushAll("articles", new Object[] { article }),
                FeedEntity.class);
    }

    public List<UserArticleEntity> getAllArticles(final List<String> feednames) {
        final AggregationResults<UserArticleEntity> results = mongoTemplate.aggregate(
                newAggregation(match(Criteria.where("feedname").in(feednames)), unwind("articles")),
                FeedEntity.class, UserArticleEntity.class);
        return results.getMappedResults();
    }
}
