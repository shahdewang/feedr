package com.bufferworks.feeds.restapi.resource;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.bufferworks.feeds.utils.UriBuilder;

/**
 * Abstract implementation of a REST resource.
 */
public abstract class AbstractBaseResource {

    @Inject
    protected HttpServletRequest request;

    @Autowired
    protected UriBuilder uriBuilder;

    public static final String DEFAULT_PAGE_START_INDEX = "0";
    public static final String DEFAULT_PAGE_SIZE = "20";
}
