package com.bufferworks.feeds.restapi.media;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AbstractListMediaType<T> extends AbstractMediaType {

    protected List<T> items = new ArrayList<>();

    public List<T> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void setItems(List<T> items) {
        this.items.addAll(items);
    }

    @JsonIgnore
    public boolean isEmpty() {
        return (items != null && !items.isEmpty());
    }

    public int count() {
        return items.size();
    }
}
