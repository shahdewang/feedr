package com.bufferworks.feeds.restapi.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bufferworks.feeds.persist.ArticlesDataService;
import com.bufferworks.feeds.persist.UsersDataService;
import com.bufferworks.feeds.persist.entity.UserArticleEntity;
import com.bufferworks.feeds.persist.entity.UserEntity;
import com.bufferworks.feeds.restapi.Paths;
import com.bufferworks.feeds.restapi.media.UserArticle;
import com.bufferworks.feeds.restapi.media.UserArticles;
import com.bufferworks.feeds.restapi.media.helper.UserArticleConverter;

@Path(Paths.USER_ARTICLES_PATH)
@Component
@Scope("prototype")
public class UserArticlesResource {

    @Autowired
    private ArticlesDataService articlesDataService;

    @Autowired
    private UsersDataService usersDataService;

    @Autowired
    private UserArticleConverter userArticleConverter;

    @GET
    @Produces(UserArticles.MEDIA_TYPE_NAME)
    public Response getFeeds(@PathParam(Paths.USER_ID) String username) {
        final UserEntity user = usersDataService.findOne(username);
        if (user == null)
            return Response.status(Status.NOT_FOUND).build();

        final List<UserArticleEntity> allArticleEntities = articlesDataService.getAllArticles(user.getSubscriptions());
        final List<UserArticle> userArticleList = userArticleConverter.toUserArticles(allArticleEntities);
        return Response.ok(new UserArticles(userArticleList)).build();
    }
}
