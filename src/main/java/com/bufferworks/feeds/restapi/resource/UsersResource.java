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

import com.bufferworks.feeds.persist.UsersDataService;
import com.bufferworks.feeds.persist.entity.UserEntity;
import com.bufferworks.feeds.restapi.Paths;
import com.bufferworks.feeds.restapi.media.User;
import com.bufferworks.feeds.restapi.media.Users;
import com.bufferworks.feeds.restapi.media.helper.UserConverter;

/**
 * Resource for handling Users.
 */
@Path(Paths.USERS_PATH)
@Component
@Scope("prototype")
public class UsersResource extends AbstractBaseResource {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UsersDataService usersDataService;

    @PUT
    @Produces(User.MEDIA_TYPE_NAME)
    @Consumes(User.MEDIA_TYPE_NAME)
    public Response createUser(User user) {
        if (usersDataService.findOne(user.getUsername()) != null)
            return Response.status(Status.CONFLICT).build();
        final UserEntity userEntity = usersDataService.save(userConverter.toUserEntity(user));
        final URI locationURI = uriBuilder.buildAbsoluteUrl(request, UserResource.class, userEntity.getUsername());
        return Response.created(locationURI).entity(userConverter.toUser(userEntity)).build();
    }

    @GET
    @Produces(Users.MEDIA_TYPE_NAME)
    public Response getUsers() {
        final List<UserEntity> userEntities = usersDataService.findAll();
        return Response.ok(userConverter.toUsers(userEntities)).build();
    }

    @DELETE
    public Response deleteAllUsers() {
        usersDataService.deleteAll();
        return Response.noContent().build();
    }
}
