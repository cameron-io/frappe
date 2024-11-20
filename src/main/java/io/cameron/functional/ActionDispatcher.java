package io.cameron.functional;

@FunctionalInterface
public interface ActionDispatcher {
    String handle(String msg);
}
