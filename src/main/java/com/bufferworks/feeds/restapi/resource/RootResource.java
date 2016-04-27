package com.bufferworks.feeds.restapi.resource;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bufferworks.feeds.restapi.Root;

@Path("/")
@Component
@Scope("prototype")
@PermitAll
public class RootResource extends AbstractBaseResource {

    @GET
    @Produces(Root.MEDIA_TYPE_NAME_JSON)
    public Response getRoot() {
        final Root root = new Root();
        root.setSelfUri(uriBuilder.buildRelativeUri(RootResource.class));
        root.setUsersUri(uriBuilder.buildRelativeUri(UsersResource.class));
        root.setFeedsUri(uriBuilder.buildRelativeUri(FeedsResource.class));
        root.setArticlesUri(uriBuilder.buildRelativeUri(ArticlesResource.class));
        root.setSubsriptionsUri(uriBuilder.buildRelativeUri(SubscriptionsResource.class));
        return Response.ok(root).build();
    }
}
