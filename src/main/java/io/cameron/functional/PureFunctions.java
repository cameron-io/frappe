package io.cameron.functional;

import java.util.function.Function;

public class PureFunctions {
    /*
     * JS-style Function<T, R> declaration requires '.apply(arg)'
     */

    // Built-in class only supports single arg
    public static final Function<Integer, Integer> doubleIt = (n) -> {
        return n * 2;
    };
    // Custom IFunction interface with 2 args
    public static final IFunction<Integer, Integer, Boolean> areEqual = (n1, n2) -> {
        return n1 == n2;
    };
}
