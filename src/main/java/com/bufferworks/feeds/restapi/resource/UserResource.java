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

import com.bufferworks.feeds.persist.UsersDataService;
import com.bufferworks.feeds.persist.entity.UserEntity;
import com.bufferworks.feeds.restapi.Paths;
import com.bufferworks.feeds.restapi.media.User;
import com.bufferworks.feeds.restapi.media.helper.UserConverter;

@Path(Paths.USER_PATH)
@Component
@Scope("prototype")
public class UserResource extends AbstractBaseResource {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UsersDataService usersDataService;

    @GET
    @Produces(User.MEDIA_TYPE_NAME)
    public Response getUser(@PathParam(Paths.USER_ID) String username) {
        final UserEntity userEntity = usersDataService.findOne(username);
        if (userEntity == null)
            return Response.status(Status.NOT_FOUND).build();
        return Response.ok(userConverter.toUser(userEntity)).build();
    }

    @DELETE
    public Response deleteUser(@PathParam(Paths.USER_ID) String username) {
        usersDataService.delete(username);
        return Response.noContent().build();
    }
}
