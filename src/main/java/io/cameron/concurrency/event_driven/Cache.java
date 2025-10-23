package io.cameron.concurrency.event_driven;

import java.util.List;

public interface Cache<T> {

    public List<T> getAll();

    public T getItem(String key);

    public void upsert(String key, T value);

    public void delete(String key);
}
