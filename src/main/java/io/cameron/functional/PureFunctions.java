package io.cameron.functional;

import java.util.function.Function;
import io.cameron.functional.interfaces.Function2;

public class PureFunctions {
    /*
     * JS-style Function<T, R> declaration requires '.apply(arg)'
     */

    // Built-in class only supports single arg
    public static final Function<Integer, Integer> doubleIt = (n) -> {
        return n * 2;
    };
    // Custom IFunction interface with 2 args
    public static final Function2<Integer, Integer, Boolean> areEqual = (n1, n2) -> {
        return n1 == n2;
    };
}
