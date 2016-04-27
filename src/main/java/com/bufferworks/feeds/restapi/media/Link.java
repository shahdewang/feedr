package com.bufferworks.feeds.restapi.media;

import java.io.Serializable;
import java.net.URI;

/**
 * Represents links in the REST entities.
 */
public class Link implements Serializable {

    private URI href;

    @SuppressWarnings("unused")
    private Link() {
    }

    public Link(URI href) {
        this.href = href;
    }

    public URI getHref() {
        return href;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((href == null) ? 0 : href.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Link other = (Link) obj;
        if (href == null) {
            if (other.href != null)
                return false;
        } else if (!href.equals(other.href))
            return false;
        return true;
    }
}
