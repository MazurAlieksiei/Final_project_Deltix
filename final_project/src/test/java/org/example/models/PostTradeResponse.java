package org.example.models;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Objects;

public class PostTradeResponse {

    List<Item> items;
    Boolean hasMore;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostTradeResponse that = (PostTradeResponse) o;

        return CollectionUtils.isEqualCollection(this.items, that.items) && Objects.equals(hasMore, that.hasMore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items, hasMore);
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
