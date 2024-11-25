package io.cameron;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import io.cameron.threading.virtual.Action;
import io.cameron.threading.virtual.Actor;
import io.cameron.threading.virtual.Message;

public class ThreadingVirtualTest {

    void sendMessage(Actor actor, Message message) {
        actor.handle(message);
    }

    @Test
    public void actorTest() throws InterruptedException {
        Actor actor = new Actor();
        // platform thread
        actor.start();

        // TODO: messages must be handled by virtual threads
        sendMessage(actor, new Message(Action.INSERT, 5));
        sendMessage(actor, new Message(Action.INSERT, 2));
        sendMessage(actor, new Message(Action.INSERT, 3));

        assertEquals(true, actor.isAlive());
        assertEquals(List.of(5, 2, 3), actor.getNumbers());

        sendMessage(actor, new Message(Action.EXIT));

        actor.join();
        assertEquals(false, actor.isAlive());
    }
}
