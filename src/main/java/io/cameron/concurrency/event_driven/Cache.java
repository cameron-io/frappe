package io.cameron.concurrency.event_driven;

import java.util.Collection;

public interface Cache<T> {
    public Collection<T> getAll();

    public void upsert(String key, T value);
}
