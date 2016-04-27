package com.bufferworks.feeds.restapi.media;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Parameters for links in a REST resource.
 */
public class LinkParams {

    protected Map<String, String> linkParams;

    @JsonCreator
    public LinkParams(final Map<String, String> linkParams) {
        this.linkParams = linkParams;
    }

    protected LinkParams() {
        linkParams = new HashMap<String, String>();
    }

    @JsonValue
    public Map<String, String> getLinkParams() {
        return linkParams;
    }

    @JsonIgnore
    public String get(String key) {
        return linkParams.get(key);
    }
}
