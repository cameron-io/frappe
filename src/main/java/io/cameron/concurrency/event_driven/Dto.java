package io.cameron.concurrency.event_driven;

public class Dto<T> {
    public String key;
    public T value;

    public Dto(String key, T value) {
        super();
        this.key = key;
        this.value = value;
    }
}
