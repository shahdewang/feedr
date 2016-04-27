package com.bufferworks.feeds.restapi.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bufferworks.feeds.persist.ArticlesDataService;
import com.bufferworks.feeds.persist.FeedsDataService;
import com.bufferworks.feeds.persist.entity.FeedEntity;
import com.bufferworks.feeds.restapi.Paths;
import com.bufferworks.feeds.restapi.media.Article;
import com.bufferworks.feeds.restapi.media.helper.ArticleConverter;

@Path(Paths.ARTICLES_PATH)
@Component
@Scope("prototype")
public class ArticlesResource extends AbstractBaseResource {

    @Autowired
    private ArticleConverter articleConverter;

    @Autowired
    private ArticlesDataService articlesDataService;

    @Autowired
    private FeedsDataService feedsDataService;

    @PUT
    @Path("/{" + Paths.FEED_ID + "}")
    @Produces(Article.MEDIA_TYPE_NAME)
    @Consumes(Article.MEDIA_TYPE_NAME)
    public Response createArticle(@PathParam(Paths.FEED_ID) String feedname, Article article) {
        final FeedEntity feed = feedsDataService.findOne(feedname);
        if (feed == null)
            return Response.status(Status.NOT_FOUND).build();

        articlesDataService.addArticle(feed.getFeedname(), articleConverter.toArticleEntity(article));
        final URI locationURI = uriBuilder.buildAbsoluteUrl(request, ArticlesResource.class, feed.getFeedname());
        return Response.created(locationURI).entity(article).build();
    }
}
