package com.bufferworks.feeds.restapi.resource;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.bufferworks.feeds.restapi.media.Article;
import com.bufferworks.feeds.restapi.media.Articles;
import com.bufferworks.feeds.restapi.media.User;
import com.bufferworks.feeds.restapi.media.UserArticles;

@RunWith(SpringRunner.class)
public class UserArticlesResourceIntegrationTest extends AbstractResourceIntegrationTest {

    @Test
    public void shouldReturnAllArticlesFollowedByUser() {
        // add a new user
        final String user = "user1";
        Assertions.assertThat(addUser(user).getStatusCode()).isEqualTo(HttpStatus.CREATED);

        // generate names for test feeds
        final List<String> feeds = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            feeds.add("Followed" + i);
            feeds.add("Unfollowed" + i);
        }

        int id = 1;
        // the UserArticles API should have these Id's
        final List<Integer> articledIds = new ArrayList<>();
        for (String feedname : feeds) {
            // create the feed
            Assertions.assertThat(addFeed(feedname).getStatusCode()).isEqualTo(HttpStatus.CREATED);
            // for each feed, create 5 articles
            for (int i = 1; i <= 5; i++) {
                if (feedname.startsWith("Followed")) {
                    articledIds.add(id);
                }
                Assertions.assertThat(restTemplate.exchange(buildUrl("/articles/" + feedname), HttpMethod.PUT,
                        new HttpEntity<Article>(new Article(id++, feedname + " Title " + i, feedname + " Content " + i),
                                headers(Article.MEDIA_TYPE_NAME, Article.MEDIA_TYPE_NAME)),
                        Article.class).getStatusCode()).isEqualTo(HttpStatus.CREATED);
            }

            if (feedname.startsWith("Followed")) {
                // all fields starting with "Followed" are followed by the user
                Assertions.assertThat(restTemplate.exchange(
                        buildUrl("/subscriptions/" + user + "/" + feedname + "/subscribe"), HttpMethod.PUT,
                        new HttpEntity<User>(headers(User.MEDIA_TYPE_NAME, null)), User.class).getStatusCode())
                        .isEqualTo(HttpStatus.OK);
            }
        }

        final ResponseEntity<UserArticles> articles = restTemplate.exchange(buildUrl("/users/" + user + "/articles"),
                HttpMethod.GET, new HttpEntity<Articles>(headers(UserArticles.MEDIA_TYPE_NAME, null)), UserArticles.class);
        Assertions.assertThat(articles.getBody().getItems()).hasSize(articledIds.size());
    }
}
