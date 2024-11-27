package io.cameron.functional.interfaces;

@FunctionalInterface
public interface Function4<T, U, V, W, R> {
    R apply(T t, U u, V v, W w);
}
