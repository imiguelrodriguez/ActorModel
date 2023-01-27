package actors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RingActorTest {

    @Test
    void RingActor() {
        ActorContext context = ActorContext.getInstance();
        ActorProxy ap = context.spawnActor("InsultActor", new RingActor(true));
        assertTrue(((RingActor) ap.getActor()).isLast());
    }
}