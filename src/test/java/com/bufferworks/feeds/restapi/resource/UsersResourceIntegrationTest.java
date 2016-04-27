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
import com.bufferworks.feeds.restapi.media.Users;

@RunWith(SpringRunner.class)
public class UsersResourceIntegrationTest extends AbstractResourceIntegrationTest {

    @Test
    public void shouldNotHaveAnyUsers() {
        final ResponseEntity<Users> entity = restTemplate.exchange(buildUrl("/users"), HttpMethod.GET,
                new HttpEntity<String>(headers(Users.MEDIA_TYPE_NAME, null)), Users.class);
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(entity.getBody()).isNotNull();
        Assertions.assertThat(entity.getBody().count()).isEqualTo(0);
    }

    @Test
    public void shouldNotAddDuplicateUsers() {
        final String user1 = "user1";
        final ResponseEntity<User> entity = addUser(user1);
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(entity.getBody()).isNotNull();
        Assertions.assertThat(entity.getBody().getUsername()).isEqualTo(user1);

        final ResponseEntity<User> entity1 = addUser(user1);
        Assertions.assertThat(entity1.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    public void shouldAddUser() {
        final String username = "user1";
        final ResponseEntity<User> entity = addUser(username);
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(entity.getBody()).isNotNull();
        Assertions.assertThat(entity.getBody().getUsername()).isEqualTo(username);
    }
}
