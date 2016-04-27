package com.bufferworks.feeds.restapi;

import java.net.URI;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.bufferworks.feeds.restapi.media.AbstractMediaType;
import com.bufferworks.feeds.restapi.media.Link;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents the root node (jersey/api/) with ability to discover sub-resources
 * using the links.
 */
@XmlRootElement(namespace = AbstractMediaType.API_VERSION)
@XmlType(namespace = AbstractMediaType.API_VERSION)
public class Root extends AbstractMediaType {

    private static final long serialVersionUID = -8677906825925819097L;

    public static final String MEDIA_TYPE_NAME_JSON = MEDIA_TYPE_NAME_BASE + "root" + SERIALIZATION_FORMAT_JSON;
    private static final String REL_USERS = "bw-users";
    private static final String REL_FEEDS = "bw-feeds";
    private static final String REL_ARTICLES = "bw-articles";
    private static final String REL_SUBSCRIPTIONS = "bw-subscriptions";

    public void setUsersUri(URI uri) {
        addLink(REL_USERS, new Link(uri));
    }

    @JsonIgnore
    public URI getUsersUri() {
        return getUri(REL_USERS);
    }

    public void setFeedsUri(URI uri) {
        addLink(REL_FEEDS, new Link(uri));
    }

    @JsonIgnore
    public URI getFeedsUri() {
        return getUri(REL_FEEDS);
    }

    public void setArticlesUri(URI uri) {
        addLink(REL_ARTICLES, new Link(uri));
    }

    @JsonIgnore
    public URI getArticlesUri() {
        return getUri(REL_ARTICLES);
    }

    public void setSubsriptionsUri(URI uri) {
        addLink(REL_SUBSCRIPTIONS, new Link(uri));
    }

    @JsonIgnore
    public URI getSubscriptionsUri() {
        return getUri(REL_SUBSCRIPTIONS);
    }
}
