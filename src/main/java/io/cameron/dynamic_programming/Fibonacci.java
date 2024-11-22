package io.cameron.dynamic_programming;

public class Fibonacci {
    public static final Integer calculate(Integer n) {
        if (n <= 2)
            return 1;

        Integer[] acc = new Integer[n + 1];
        acc[0] = 0;
        acc[1] = 1;
        acc[2] = 1;

        for (int i = 3; i <= n; i++) {
            acc[i] = acc[i - 1] + acc[i - 2];
        }

        return acc[n];
    };
}
