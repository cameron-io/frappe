package io.cameron.functional;

import java.util.function.Function;

public class PureFunctions {
    public static final Function<String, String> doubleString = (String s) -> {
        return s + " " + s;
    };
}
