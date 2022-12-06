package tests;

import actors.*;
import messages.*;


public class DecoratorTest {
    public static void main(String[] args) {
        ActorContext actors = ActorContext.getInstance();
        ActorProxy hello = actors.spawnActor("name", new EncryptionDecorator(new HelloWorldActor()));
        hello.send(new Message(null, "It's safe 'cause its ciphered."));
        hello.send(new QuitMessage(null, "Quitting hello."));
    }
}

