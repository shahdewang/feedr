package com.bufferworks.feeds.restapi.media.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bufferworks.feeds.persist.entity.UserEntity;
import com.bufferworks.feeds.restapi.media.User;
import com.bufferworks.feeds.restapi.media.Users;
import com.bufferworks.feeds.restapi.resource.UserResource;
import com.bufferworks.feeds.restapi.resource.UsersResource;
import com.bufferworks.feeds.utils.UriBuilder;

@Component
public class UserConverter {

    @Autowired
    private UriBuilder uriBuilder;

    public UserEntity toUserEntity(final User user) {
        return new UserEntity(user.getUsername(), user.getSubscriptions());
    }

    public User toUser(final UserEntity userEntity) {
        final User user = new User(userEntity.getUsername(), userEntity.getSubscriptions());
        user.setSelfUri(uriBuilder.buildRelativeUri(UserResource.class, user.getUsername()));
        return user;
    }

    public Users toUsers(final List<UserEntity> userEntities) {
        final List<User> userList = new ArrayList<>();
        for (UserEntity entity : userEntities) {
            userList.add(toUser(entity));
        }

        final Users users = new Users(userList);
        users.setSelfUri(uriBuilder.buildRelativeUri(UsersResource.class));
        return users;
    }
}
