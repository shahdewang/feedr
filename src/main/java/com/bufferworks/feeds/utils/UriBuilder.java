package com.bufferworks.feeds.utils;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class UriBuilder {

    public static final String SERVLET_PATH = "/jersey/api";
    public static final String CONTEXT_PATH = "";

    @SuppressWarnings("rawtypes")
    public URI buildRelativeUri(Class clazz, Object... params) {
        return javax.ws.rs.core.UriBuilder.fromPath(CONTEXT_PATH).path(SERVLET_PATH).path(clazz).build(params);
    }

    @SuppressWarnings("rawtypes")
    public URI buildAbsoluteUrl(HttpServletRequest request, Class clazz, Object... params) {
        return javax.ws.rs.core.UriBuilder.fromPath(request.getContextPath()).path(SERVLET_PATH).path(clazz)
                .build(params);
    }
}
