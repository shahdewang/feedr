package com.bufferworks.feeds.restapi.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.bufferworks.feeds.restapi.media.Feed;
import com.bufferworks.feeds.restapi.media.User;

@RunWith(SpringRunner.class)
public class SubscriptionsResourceIntegrationTest extends AbstractResourceIntegrationTest {

    @Test
    public void shouldAddMultipleSubscriptions() {
        final String user = "user1";
        final ResponseEntity<User> entity = addUser(user);
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(entity.getBody().getUsername()).isEqualTo(user);
        Assertions.assertThat(entity.getBody().getSubscriptions()).isEmpty();

        final List<String> feeds = Arrays.asList("feed1", "feed2", "feed3");
        for (String feed : feeds) {
            final ResponseEntity<Feed> feedResponse = addFeed(feed);
            Assertions.assertThat(feedResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
            Assertions.assertThat(feedResponse.getBody().getFeedname()).isEqualTo(feed);
        }

        final List<String> subscribedFeeds = new ArrayList<>();
        for (String feed : feeds) {
            final ResponseEntity<User> response = restTemplate.exchange(
                    buildUrl("/subscriptions/" + user + "/" + feed + "/subscribe"), HttpMethod.PUT,
                    new HttpEntity<User>(headers(User.MEDIA_TYPE_NAME, null)),
                    User.class);
            Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            subscribedFeeds.add(feed);
            Assertions.assertThat(response.getBody().getSubscriptions()).hasSameElementsAs(subscribedFeeds);
        }
    }

    @Test
    public void shouldRemoveMultipleSubscriptions() {
        final String user = "user1";
        final ResponseEntity<User> entity = addUser(user);
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(entity.getBody().getUsername()).isEqualTo(user);
        Assertions.assertThat(entity.getBody().getSubscriptions()).isEmpty();

        final List<String> feeds = Arrays.asList("feed1", "feed2", "feed3");
        for (String feed : feeds) {
            final ResponseEntity<Feed> feedResponse = addFeed(feed);
            Assertions.assertThat(feedResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
            Assertions.assertThat(feedResponse.getBody().getFeedname()).isEqualTo(feed);
        }

        // First add all subscriptions
        final List<String> subscribedFeeds = new ArrayList<>();
        for (String feed : feeds) {
            final ResponseEntity<User> response = restTemplate.exchange(
                    buildUrl("/subscriptions/" + user + "/" + feed + "/subscribe"), HttpMethod.PUT,
                    new HttpEntity<User>(headers(User.MEDIA_TYPE_NAME, null)), User.class);
            Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            subscribedFeeds.add(feed);
            Assertions.assertThat(response.getBody().getSubscriptions()).hasSameElementsAs(subscribedFeeds);
        }

        // subscribedFeeds has all feeds the user is subscribed to
        // Now remove one feed at a time and also remove from subscribedFeeds list, the remaining list should match
        for (String feed : feeds) {
            final ResponseEntity<User> response = restTemplate.exchange(
                    buildUrl("/subscriptions/" + user + "/" + feed + "/unsubscribe"), HttpMethod.PUT,
                    new HttpEntity<User>(headers(User.MEDIA_TYPE_NAME, null)), User.class);
            Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            subscribedFeeds.remove(feed);
            Assertions.assertThat(response.getBody().getSubscriptions()).hasSameElementsAs(subscribedFeeds);
        }
    }
}
