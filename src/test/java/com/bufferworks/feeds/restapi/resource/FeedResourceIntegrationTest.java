package com.bufferworks.feeds.restapi.resource;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.bufferworks.feeds.restapi.media.Feed;

@RunWith(SpringRunner.class)
public class FeedResourceIntegrationTest extends AbstractResourceIntegrationTest {

    @Test
    public void shouldGetFeed() {
        final String feedname = "feed1";
        Assertions.assertThat(addFeed(feedname).getStatusCode()).isEqualTo(HttpStatus.CREATED);

        final ResponseEntity<Feed> entity = restTemplate.exchange(buildUrl("/feeds/" + feedname), HttpMethod.GET,
                new HttpEntity<String>(headers(Feed.MEDIA_TYPE_NAME, null)), Feed.class);
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(entity.getBody().getFeedname()).isEqualTo(feedname);
    }

    @Test
    public void shouldDeleteFeed() {
        final String feedname = "feed1";
        Assertions.assertThat(addFeed(feedname).getStatusCode()).isEqualTo(HttpStatus.CREATED);

        final ResponseEntity<Object> deleteEntity = restTemplate.exchange(buildUrl("/feeds/" + feedname),
                HttpMethod.DELETE, new HttpEntity<String>(headers(null, null)), Object.class);
        Assertions.assertThat(deleteEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        final ResponseEntity<Feed> entity = restTemplate.exchange(buildUrl("/feeds/" + feedname), HttpMethod.GET,
                new HttpEntity<String>(headers(Feed.MEDIA_TYPE_NAME, null)), Feed.class);
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
