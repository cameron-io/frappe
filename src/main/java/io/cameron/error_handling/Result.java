package io.cameron.error_handling;

public class Result<T> {
    private final T value;
    private final Exception error;

    public Result(T value, Exception error) {
        this.value = value;
        this.error = error;
    }

    public T getValue() {
        return value;
    }

    public Exception getError() {
        return error;
    }
}
