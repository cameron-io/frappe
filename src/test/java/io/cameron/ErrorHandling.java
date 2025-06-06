package io.cameron;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;
import io.cameron.error_handling.Result;

public class ErrorHandling {
    @Test
    public void resultTest() {
        if (doSomethingOk().getError() != null) {
            // handle the error
        }
        if (doSomethingError().getError() != null) {
            // handle the error
        }

        Throwable err = doSomethingError().getError();

        assertNotEquals(err, null);
    }

    private Result<Integer> doSomethingOk() {
        return new Result<Integer>(5, null);
    }

    private Result<Integer> doSomethingError() {
        return new Result<Integer>(5, new Exception("Something went wrong."));
    }
}
