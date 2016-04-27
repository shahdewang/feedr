package com.bufferworks.feeds.restapi.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bufferworks.feeds.persist.FeedsDataService;
import com.bufferworks.feeds.persist.entity.FeedEntity;
import com.bufferworks.feeds.restapi.Paths;
import com.bufferworks.feeds.restapi.media.Feed;
import com.bufferworks.feeds.restapi.media.Feeds;
import com.bufferworks.feeds.restapi.media.helper.FeedConverter;

/**
 * Resource for handling Feeds.
 */
@Path(Paths.FEEDS_PATH)
@Component
@Scope("prototype")
public class FeedsResource extends AbstractBaseResource {

    @Autowired
    private FeedConverter feedConverter;

    @Autowired
    private FeedsDataService feedsDataService;

    @PUT
    @Produces(Feed.MEDIA_TYPE_NAME)
    @Consumes(Feed.MEDIA_TYPE_NAME)
    public Response createFeed(Feed feed) {
        if (feedsDataService.findOne(feed.getFeedname()) != null)
            return Response.status(Status.CONFLICT).build();

        final FeedEntity feedEntity = feedsDataService.save(feedConverter.toFeedEntity(feed));
        final URI locationURI = uriBuilder.buildAbsoluteUrl(request, FeedResource.class, feedEntity.getFeedname());
        return Response.created(locationURI).entity(feedConverter.toFeed(feedEntity)).build();
    }

    @GET
    @Produces(Feeds.MEDIA_TYPE_NAME)
    public Response getFeeds() {
        final List<FeedEntity> feedEntities = feedsDataService.findAllFeeds();
        return Response.ok(feedConverter.toFeeds(feedEntities)).build();
    }

    @DELETE
    public Response deleteAllFeeds() {
        feedsDataService.deleteAll();
        return Response.noContent().build();
    }
}
