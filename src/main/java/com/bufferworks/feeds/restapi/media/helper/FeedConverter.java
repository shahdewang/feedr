package com.bufferworks.feeds.restapi.media.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bufferworks.feeds.persist.entity.FeedEntity;
import com.bufferworks.feeds.restapi.media.Feed;
import com.bufferworks.feeds.restapi.media.Feeds;
import com.bufferworks.feeds.restapi.resource.FeedResource;
import com.bufferworks.feeds.restapi.resource.FeedsResource;
import com.bufferworks.feeds.utils.UriBuilder;

@Component
public class FeedConverter {

    @Autowired
    protected UriBuilder uriBuilder;

    @Autowired
    private ArticleConverter articleConverter;

    public FeedEntity toFeedEntity(final Feed feed) {
        return new FeedEntity(feed.getFeedname(), articleConverter.toArticleEntities(feed.getArticles()));
    }

    public Feed toFeed(final FeedEntity feedEntity) {
        final Feed feed = new Feed(feedEntity.getFeedname(),
                articleConverter.toArticles(feedEntity.getFeedname(), feedEntity.getArticles()).getItems());
        feed.setSelfUri(uriBuilder.buildRelativeUri(FeedResource.class, feed.getFeedname()));
        return feed;
    }

    public Feeds toFeeds(final List<FeedEntity> feedEntities) {
        final List<Feed> feedList = new ArrayList<>();
        for (FeedEntity entity : feedEntities) {
            feedList.add(toFeed(entity));
        }

        final Feeds feeds = new Feeds(feedList);
        feeds.setSelfUri(uriBuilder.buildRelativeUri(FeedsResource.class));
        return feeds;
    }
}
