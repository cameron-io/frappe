package io.cameron.concurrency.event_driven;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheService implements Cache<Integer> {

    private static CacheService instance;
    Map<String, Integer> data = new HashMap<>();

    private CacheService() {
    }

    public static CacheService getInstance() {
        synchronized (CacheService.class) {
            if (instance == null) {
                instance = new CacheService();
            }
            return instance;
        }
    }

    @Override
    public List<Integer> getAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Integer getItem(String key) {
        return data.get(key);
    }

    @Override
    synchronized public void upsert(String key, Integer value) {
        data.putIfAbsent(key, value);
    }

    @Override
    synchronized public void delete(String key) {
        data.remove(key);
    }
}
