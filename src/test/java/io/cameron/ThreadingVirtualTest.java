package io.cameron;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import io.cameron.threading.virtual.Action;
import io.cameron.threading.virtual.Actor;
import io.cameron.threading.virtual.Message;

public class ThreadingVirtualTest {

    @Test
    public void actorTest() throws InterruptedException {
        Actor actor = new Actor();
        actor.start();

        actor.handle(new Message(Action.INSERT, 5));
        actor.handle(new Message(Action.INSERT, 2));
        actor.handle(new Message(Action.INSERT, 3));

        assertEquals(true, actor.isAlive());
        assertEquals(List.of(5, 2, 3), actor.getNumbers());

        actor.handle(new Message(Action.EXIT));

        Thread.sleep(100);

        assertEquals(false, actor.isAlive());
    }
}
