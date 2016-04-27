package com.bufferworks.feeds.restapi.media;

import java.io.Serializable;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * A base class with utility methods to configure links for a REST entity.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlType(namespace = AbstractMediaType.API_VERSION)
public abstract class AbstractMediaType implements Serializable {

    public static final String API_VERSION = "http://www.bufferworks.com/feeds/v1.0";
    public static final String MEDIA_TYPE_NAME_BASE = "application/com.bufferworks.";
    public static final String SERIALIZATION_FORMAT_JSON = "+json";

    public static final String REL_SELF = "self";

    @JsonProperty("_links")
    @JsonDeserialize(as = HashMap.class, keyAs = String.class, contentAs = Link.class)
    private Map<String, Link> links = new LinkedHashMap<String, Link>();

    public Map<String, Link> getLinks() {
        return Collections.unmodifiableMap(links);
    }

    @JsonIgnore
    public void setSelfUri(final URI href) {
        addLink(REL_SELF, new Link(href));
    }

    @JsonIgnore
    public URI getSelfUri() {
        return getUri(REL_SELF);
    }

    @JsonIgnore
    public URI getUri(final String relation) {
        final Link link = getLink(relation);
        return link == null ? null : link.getHref();
    }

    @JsonIgnore
    public void addLink(final String relation, Link link) {
        this.links.put(relation, link);
    }

    @JsonIgnore
    public Link getLink(String rel) {
        return links.get(rel);
    }
}
