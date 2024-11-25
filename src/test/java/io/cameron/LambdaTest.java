package io.cameron;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import io.cameron.functional.ActionDispatcher;
import io.cameron.functional.PureFunctions;

public class LambdaTest {
    @Test
    public void actionDispatcherTest() {
        ActionDispatcher<String> actionDispatcher1 = (String s) -> s == "Start";
        assertEquals(true, actionDispatcher1.handle("Start"));

        ActionDispatcher<String> actionDispatcher2 = (String s) -> s != "Continue";
        ActionDispatcher<String> actionDispatcher3 = (String s) -> s == "Exit";
        assertEquals(true, actionDispatcher2.handleBoth(actionDispatcher3).handle("Exit"));
    }

    @Test
    public void predicateTest() {
        Predicate<String> predicate = (String s) -> s == "!";

        assertEquals(true, predicate.test("!"));
        assertEquals(false, predicate.test("Test"));
    }

    @Test
    public void compoundPredicateTest() {
        Predicate<String> predicate1 = (String s) -> s == "!";
        Predicate<String> predicate2 = (String s) -> s == "?";

        assertEquals(false, predicate1.and(predicate2).test("!"));
        assertEquals(true, predicate1.or(predicate2).test("!"));
        assertEquals(true, predicate1.negate().test("Test"));
    }

    @Test
    public void listFilterTest() {
        List<String> list = List.of("one", "two", "three", "four");
        Predicate<String> predicate = (String s) -> s == "three";

        List<String> results = list.stream().filter(predicate).collect(Collectors.toList());

        assertEquals(1, results.size());
        assertEquals("three", results.get(0));
    }

    @Test
    public void listComprehensionTest() {
        final List<String> list = List.of("one", "two", "three", "four");
        final Function<String, Boolean> predicate = (String s) -> s == "three";

        final List<Boolean> results = list.stream().map(predicate).collect(Collectors.toList());

        assertEquals(false, results.get(0));
        assertEquals(false, results.get(1));
        assertEquals(true, results.get(2));
        assertEquals(false, results.get(3));
    }

    @Test
    public void pureFunctionsTest() {
        assertEquals(10, PureFunctions.doubleIt.apply(5));
        assertEquals(true, PureFunctions.areEqual.apply(5, 5));
        assertEquals(false, PureFunctions.areEqual.apply(1, 5));
    }
}
