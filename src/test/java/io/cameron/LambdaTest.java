package io.cameron;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import io.cameron.functional.ActionDispatcher;

public class LambdaTest {
    @Test
    public void actionDispatcherTest() {
        ActionDispatcher<String> actionDispatcher = 
            (String s) -> true;

        assertEquals(true, actionDispatcher.handle("Test"));
    }
    
    @Test
    public void predicateTest() {
        Predicate<String> predicate = 
            (String s) -> s == "!";

        assertEquals(true, predicate.test("!"));
        assertEquals(false, predicate.test("Test"));
    }
    
    @Test
    public void listFilterTest() {
        List<String> list = List.of("one", "two", "three", "four");
        Predicate<String> predicate =
            (String s) -> s == "three";

        List<String> results = list.stream().filter(predicate).collect(Collectors.toList());

        assertEquals(1, results.size());
        assertEquals("three", results.get(0));
    }
    
    @Test
    public void listComprehensionTest() {
        List<String> list = List.of("one", "two", "three", "four");
        Function<String, Boolean> predicate =
            (String s) -> s == "three";

        List <Boolean> results = list.stream().map(predicate).collect(Collectors.toList());

        assertEquals(false, results.get(0));
        assertEquals(false, results.get(1));
        assertEquals(true, results.get(2));
        assertEquals(false, results.get(3));
    }
}
