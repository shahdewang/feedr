package com.bufferworks.feeds.restapi.resource;

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

@RunWith(SpringRunner.class)
public class ArticlesResourceIntegrationTest extends AbstractResourceIntegrationTest {

    @Test
    public void shouldAddArticleToFeed() {
        final String feed1 = "feed1";
        final ResponseEntity<Feed> entity = addFeed(feed1);
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(entity.getBody()).isNotNull();
        Assertions.assertThat(entity.getBody().getFeedname()).isEqualTo(feed1);
        Assertions.assertThat(entity.getBody().getArticles()).isEmpty();

        final ResponseEntity<Article> article = restTemplate.exchange(buildUrl("/articles/" + feed1), HttpMethod.PUT,
                new HttpEntity<Article>(new Article(1, "title", "content"),
                        headers(Article.MEDIA_TYPE_NAME, Article.MEDIA_TYPE_NAME)),
                Article.class);
        Assertions.assertThat(article.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(article.getBody()).isNotNull();

        final ResponseEntity<Feed> updatedEntity = restTemplate.exchange(buildUrl("/feeds/" + feed1), HttpMethod.GET,
                new HttpEntity<String>(headers(Feed.MEDIA_TYPE_NAME, null)), Feed.class);
        Assertions.assertThat(updatedEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(updatedEntity.getBody().getFeedname()).isEqualTo(feed1);
        Assertions.assertThat(updatedEntity.getBody().getArticles()).hasSize(1);
    }
}
