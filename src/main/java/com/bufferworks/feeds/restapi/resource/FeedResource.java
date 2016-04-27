package com.bufferworks.feeds.restapi.resource;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
import com.bufferworks.feeds.restapi.media.helper.FeedConverter;

@Path(Paths.FEED_PATH)
@Component
@Scope("prototype")
public class FeedResource extends AbstractBaseResource {

    @Autowired
    private FeedConverter feedConverter;

    @Autowired
    private FeedsDataService feedsDataService;

    @GET
    @Produces(Feed.MEDIA_TYPE_NAME)
    public Response getFeed(@PathParam(Paths.FEED_ID) String feedname) {
        final FeedEntity feedEntity = feedsDataService.findOne(feedname);
        if (feedEntity == null)
            return Response.status(Status.NOT_FOUND).build();
        return Response.ok(feedConverter.toFeed(feedEntity)).build();
    }

    @DELETE
    public Response deleteFeed(@PathParam(Paths.FEED_ID) String feedname) {
        feedsDataService.delete(feedname);
        return Response.noContent().build();
    }
}
