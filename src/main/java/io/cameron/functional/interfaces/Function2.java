package io.cameron.functional.interfaces;

@FunctionalInterface
public interface Function2<T, U, R> {
    R apply(T t, U u);
}
