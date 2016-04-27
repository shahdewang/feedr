package com.bufferworks.feeds.persist;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bufferworks.feeds.persist.entity.UserEntity;

public interface UsersDataService extends MongoRepository<UserEntity, String> {

}
