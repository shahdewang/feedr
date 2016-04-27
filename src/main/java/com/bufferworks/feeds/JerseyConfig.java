package com.bufferworks.feeds;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Configures Jersey framework.
 */
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        packages("com.bufferworks.feeds.restapi");
    }
}
