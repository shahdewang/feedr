package com.bufferworks.feeds;

import org.glassfish.jersey.server.ResourceConfig;

import com.bufferworks.feeds.restapi.resource.ArticlesResource;
import com.bufferworks.feeds.restapi.resource.FeedResource;
import com.bufferworks.feeds.restapi.resource.FeedsResource;
import com.bufferworks.feeds.restapi.resource.RootResource;
import com.bufferworks.feeds.restapi.resource.SubscriptionsResource;
import com.bufferworks.feeds.restapi.resource.UserArticlesResource;
import com.bufferworks.feeds.restapi.resource.UserResource;
import com.bufferworks.feeds.restapi.resource.UsersResource;

/**
 * Configures Jersey framework.
 */
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(RootResource.class);
        register(ArticlesResource.class);
        register(FeedResource.class);
        register(FeedsResource.class);
        register(SubscriptionsResource.class);
        register(UserArticlesResource.class);
        register(UserResource.class);
        register(UsersResource.class);
    }
}
