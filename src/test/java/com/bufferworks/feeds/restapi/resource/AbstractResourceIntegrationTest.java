package com.bufferworks.feeds.restapi.resource;

import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.springframework.boot.context.web.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.bufferworks.feeds.restapi.media.Feed;
import com.bufferworks.feeds.restapi.media.User;
import com.bufferworks.feeds.restapi.media.Users;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class AbstractResourceIntegrationTest {

    protected RestTemplate restTemplate = new TestRestTemplate();

    @LocalServerPort
    private int port;

    @Before
    public void setup() {
        for (String collection : Arrays.asList("users", "feeds")) {
            final ResponseEntity<Object> entity = restTemplate.exchange(buildUrl("/" + collection), HttpMethod.DELETE,
                    new HttpEntity<String>(headers(Users.MEDIA_TYPE_NAME, null)), Object.class);
            Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        }
    }

    protected ResponseEntity<User> addUser(String username) {
        final ResponseEntity<User> entity = restTemplate
                .exchange(buildUrl("/users"), HttpMethod.PUT,
                        new HttpEntity<User>(new User(username), headers(User.MEDIA_TYPE_NAME, User.MEDIA_TYPE_NAME)),
                        User.class);
        return entity;
    }

    protected ResponseEntity<Feed> addFeed(String feedname) {
        final ResponseEntity<Feed> entity = restTemplate
                .exchange(buildUrl("/feeds"), HttpMethod.PUT,
                        new HttpEntity<Feed>(new Feed(feedname), headers(Feed.MEDIA_TYPE_NAME, Feed.MEDIA_TYPE_NAME)),
                        Feed.class);
        return entity;
    }

    protected String buildUrl(final String path) {
        return "http://localhost:" + port + "/jersey/api" + path;
    }

    protected HttpHeaders headers(final String accept, final String contentType) {
        final HttpHeaders requestHeaders = new HttpHeaders();
        if (accept != null)
            requestHeaders.set(HttpHeaders.ACCEPT, accept);
        if (contentType != null)
            requestHeaders.set(HttpHeaders.CONTENT_TYPE, contentType);
        return requestHeaders;
    }
}
