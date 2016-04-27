package com.bufferworks.feeds.restapi.resource;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.bufferworks.feeds.restapi.media.User;

@RunWith(SpringRunner.class)
public class UserResourceIntegrationTest extends AbstractResourceIntegrationTest {

    @Test
    public void shouldGetUser() {
        final String username = "user1";
        Assertions.assertThat(addUser(username).getStatusCode()).isEqualTo(HttpStatus.CREATED);

        final ResponseEntity<User> entity = restTemplate.exchange(buildUrl("/users/" + username), HttpMethod.GET,
                new HttpEntity<String>(headers(User.MEDIA_TYPE_NAME, null)), User.class);
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(entity.getBody()).isNotNull();
        Assertions.assertThat(entity.getBody().getUsername()).isEqualTo(username);
    }

    @Test
    public void shouldDeleteUser() {
        final String username = "user1";
        Assertions.assertThat(addUser(username).getStatusCode()).isEqualTo(HttpStatus.CREATED);

        final ResponseEntity<Object> deleteEntity = restTemplate.exchange(buildUrl("/users/" + username),
                HttpMethod.DELETE, new HttpEntity<User>(headers(null, null)), Object.class);
        Assertions.assertThat(deleteEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        final ResponseEntity<User> entity = restTemplate.exchange(buildUrl("/users/" + username), HttpMethod.GET,
                new HttpEntity<String>(headers(User.MEDIA_TYPE_NAME, null)), User.class);
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
