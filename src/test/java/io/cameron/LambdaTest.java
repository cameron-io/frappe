package io.cameron;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import io.cameron.functional.ActionDispatcher;

public class LambdaTest {
    @Test
    public void lambdaTest() {
        ActionDispatcher actionDispatcher = 
            (String s) -> {
                return "Received: " + s;
            };
        assertEquals("Received: Test", actionDispatcher.handle("Test"));
    }
}
