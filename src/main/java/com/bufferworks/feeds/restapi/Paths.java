package com.bufferworks.feeds.restapi;

/**
 * Resource path constants.
 */
public class Paths {

    public static final String USERS_PATH = "/users";
    public static final String USER_ID = "userId";
    public static final String USER_PATH = "/users/{" + USER_ID + "}";

    public static final String FEEDS_PATH = "/feeds";
    public static final String FEED_ID = "feedId";
    public static final String FEED_PATH = "/feeds/{" + FEED_ID + "}";

    public static final String SUBSCRIPTIONS_PATH = "/subscriptions";
    public static final String SUBSCRIBE_PATH = "/{" + USER_ID + "}/{" + FEED_ID + "}/subscribe";
    public static final String UNSUBSCRIBE_PATH = "/{" + USER_ID + "}/{" + FEED_ID + "}/unsubscribe";

    public static final String ARTICLES_PATH = "/articles";
    public static final String ARTICLE_ID = "articleId";
    public static final String ARTICLE_PATH = "/articles/{" + FEED_ID + "}/article/{" + ARTICLE_ID + "}";

    public static final String USER_ARTICLES_PATH = "/users/{" + USER_ID + "}/articles";
}
