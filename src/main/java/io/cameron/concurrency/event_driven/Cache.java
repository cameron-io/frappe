package io.cameron.concurrency.event_driven;

import java.util.List;

public interface Cache<T> {
    public List<T> getAll();

    public void upsert(String key, T value);
}
