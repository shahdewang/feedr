package com.bufferworks.feeds.restapi.resource;

import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.bufferworks.feeds.restapi.media.Article;
import com.bufferworks.feeds.restapi.media.Feed;
import com.bufferworks.feeds.restapi.media.Feeds;
import com.bufferworks.feeds.restapi.media.Users;

@RunWith(SpringRunner.class)
public class FeedsResourceIntegrationTest extends AbstractResourceIntegrationTest {

    @Test
    public void shouldNotHaveAnyFeeds() {
        final ResponseEntity<Users> entity = this.restTemplate.exchange(buildUrl("/feeds"), HttpMethod.GET,
                new HttpEntity<String>(headers(Feeds.MEDIA_TYPE_NAME, null)), Users.class);
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(entity.getBody()).isNotNull();
        Assertions.assertThat(entity.getBody().count()).isEqualTo(0);
    }

    @Test
    public void shouldNotAddDuplicateFeeds() {
        final String feed1 = "feed1";
        final ResponseEntity<Feed> entity = addFeed(feed1);
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(entity.getBody()).isNotNull();
        Assertions.assertThat(entity.getBody().getFeedname()).isEqualTo(feed1);

        final ResponseEntity<Feed> entity1 = addFeed(feed1);
        Assertions.assertThat(entity1.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    public void shouldAddFeed() {
        final String feed1 = "feed1";
        final ResponseEntity<Feed> entity = addFeed(feed1);
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(entity.getBody()).isNotNull();
        Assertions.assertThat(entity.getBody().getFeedname()).isEqualTo(feed1);
    }

    @Test
    public void shouldAddFeedWithArticleAndConfirmArticlesAreNotReturned() {
        final String feed1 = "feed1";
        final ResponseEntity<Feed> entity = restTemplate.exchange(buildUrl("/feeds"), HttpMethod.PUT,
                new HttpEntity<Feed>(new Feed(feed1, Arrays.asList(new Article(1, "", ""))),
                        headers(Feed.MEDIA_TYPE_NAME, Feed.MEDIA_TYPE_NAME)),
                Feed.class);
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(entity.getBody()).isNotNull();
        Assertions.assertThat(entity.getBody().getFeedname()).isEqualTo(feed1);
        Assertions.assertThat(entity.getBody().getArticles()).hasSize(1);

        final ResponseEntity<Feeds> feeds = restTemplate.exchange(buildUrl("/feeds"), HttpMethod.GET,
                new HttpEntity<Feeds>(headers(Feeds.MEDIA_TYPE_NAME, null)), Feeds.class);
        Assertions.assertThat(feeds.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(feeds.getBody()).isNotNull();
        for (Feed feed : feeds.getBody().getItems()) {
            Assertions.assertThat(feed.getArticles()).isEmpty();
        }
    }
}
