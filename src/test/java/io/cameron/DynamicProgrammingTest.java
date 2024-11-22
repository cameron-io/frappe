package io.cameron;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import io.cameron.dynamic_programming.Fibonacci;

public class DynamicProgrammingTest {
    @Test
    public void fibonacciTest() {
        assertEquals(34, Fibonacci.calculate(9));
    }
}
