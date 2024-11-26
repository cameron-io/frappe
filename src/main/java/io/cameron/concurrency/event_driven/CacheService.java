package io.cameron.concurrency.event_driven;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheService implements Cache<Integer> {
    private static CacheService instance;
    Map<String, Integer> data = new HashMap<String, Integer>();

    private CacheService() {
    }

    public static CacheService getInstance() {
        if (instance == null) {
            synchronized (CacheService.class) {
                if (instance == null) {
                    instance = new CacheService();
                }
            }
        }
        return instance;
    }

    synchronized public List<Integer> getAll() {
        return new ArrayList<Integer>(data.values());
    }

    synchronized public Integer getItem(String key) {
        return data.get(key);
    }

    synchronized public void upsert(String key, Integer value) {
        data.putIfAbsent(key, value);
    }

    synchronized public void delete(String key) {
        synchronized (data) {
            data.remove(key);
        }
    }
}
