package io.cameron.functional;

@FunctionalInterface
public interface IFunction<T, U, R> {
    R apply(T t, U u);
}
