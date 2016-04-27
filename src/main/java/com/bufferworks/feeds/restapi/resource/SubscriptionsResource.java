package com.bufferworks.feeds.restapi.resource;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bufferworks.feeds.persist.FeedsDataService;
import com.bufferworks.feeds.persist.UserSubscriptionDataService;
import com.bufferworks.feeds.persist.UsersDataService;
import com.bufferworks.feeds.persist.entity.FeedEntity;
import com.bufferworks.feeds.persist.entity.UserEntity;
import com.bufferworks.feeds.restapi.Paths;
import com.bufferworks.feeds.restapi.media.User;

@Path(Paths.SUBSCRIPTIONS_PATH)
@Component
@Scope("prototype")
public class SubscriptionsResource extends AbstractBaseResource {

    @Autowired
    private FeedsDataService feedsDataService;

    @Autowired
    private UsersDataService usersDataService;

    @Autowired
    private UserSubscriptionDataService userSubscriptionDataService;

    @PUT
    @Path(Paths.SUBSCRIBE_PATH)
    @Produces(User.MEDIA_TYPE_NAME)
    public Response subscribe(@PathParam(Paths.USER_ID) String username, @PathParam(Paths.FEED_ID) String feedname) {
        final UserEntity user = usersDataService.findOne(username);
        if (user == null)
            return Response.status(Status.NOT_FOUND).build();

        final FeedEntity feed = feedsDataService.findOne(feedname);
        if (feed == null)
            return Response.status(Status.NOT_FOUND).build();

        userSubscriptionDataService.subscribe(username, feedname);
        final UserEntity updatedUser = usersDataService.findOne(username);
        return Response.ok(updatedUser).build();
    }

    @PUT
    @Path(Paths.UNSUBSCRIBE_PATH)
    public Response unsubscribe(@PathParam(Paths.USER_ID) String username, @PathParam(Paths.FEED_ID) String feedname) {
        final UserEntity user = usersDataService.findOne(username);
        if (user == null)
            return Response.status(Status.NOT_FOUND).entity("User " + username + " not found").build();

        final FeedEntity feed = feedsDataService.findOne(feedname);
        if (feed == null)
            return Response.status(Status.NOT_FOUND).entity("Feed " + feedname + " not found").build();

        userSubscriptionDataService.unsubscribe(username, feedname);
        final UserEntity updatedUser = usersDataService.findOne(username);
        return Response.ok(updatedUser).build();
    }
}
