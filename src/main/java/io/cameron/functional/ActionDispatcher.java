package io.cameron.functional;

import java.util.Objects;

@FunctionalInterface
public interface ActionDispatcher<T> {
    boolean handle(T msg);

    default ActionDispatcher<T> handleBoth(ActionDispatcher<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> handle(t) && other.handle(t);
    }
}
