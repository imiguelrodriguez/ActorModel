package tests;

import actors.*;
import messages.*;

public class ProxyTest {
    public static void main(String[] args) {
        ActorContext actors = ActorContext.getInstance();
        ActorProxy aProxy1 = actors.spawnActor("actor1", new InsultActor());
        ActorProxy aProxy2 = actors.spawnActor("actor2", new InsultActor());
        ActorProxy aProxy3 = actors.spawnActor("actor3", new HelloWorldActor());
        ActorProxy aProxy4 = actors.spawnActor("actor4", new InsultActor());
        aProxy4.send(new AddInsultMessage("Idiot"));
        aProxy1.send(new InsultMessage(null, "Fuck you!"));
        aProxy3.send(new Message(null, "First message , helloo!"));
        aProxy2.send(new InsultMessage(aProxy1.getActor(), "Dumbass!"));
        aProxy1.send(new Message(null, "This is your 2nd message."));
        aProxy2.send(new QuitMessage(null, "You're quitting proxy 2."));
        aProxy1.send(new QuitMessage(null, "You're quitting proxy1."));
    }
}
