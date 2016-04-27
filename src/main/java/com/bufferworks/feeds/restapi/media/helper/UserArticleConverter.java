package com.bufferworks.feeds.restapi.media.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bufferworks.feeds.persist.entity.UserArticleEntity;
import com.bufferworks.feeds.restapi.media.UserArticle;
import com.bufferworks.feeds.restapi.resource.ArticlesResource;
import com.bufferworks.feeds.utils.UriBuilder;

@Component
public class UserArticleConverter {

    @Autowired
    private UriBuilder uriBuilder;

    @Autowired
    private ArticleConverter articleConverter;

    public UserArticle toUserArticle(final UserArticleEntity entity) {
        final UserArticle userArticle = new UserArticle(entity.getFeedname(),
                articleConverter.toArticle(entity.getFeedname(), entity.getArticles()));
        userArticle.setSelfUri(uriBuilder.buildRelativeUri(ArticlesResource.class, entity.getFeedname()));
        return userArticle;
    }

    public List<UserArticle> toUserArticles(final List<UserArticleEntity> entities) {
        final List<UserArticle> userArticles = new ArrayList<>();
        for (UserArticleEntity entity : entities) {
            userArticles.add(toUserArticle(entity));
        }
        return userArticles;
    }
}
