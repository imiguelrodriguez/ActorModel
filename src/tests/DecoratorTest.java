package tests;

import actors.*;
import decorators.EncryptionDecorator;
import decorators.FirewallDecorator;
import messages.*;


public class DecoratorTest {
    public static void main(String[] args) throws InterruptedException {
        ActorContext actors = ActorContext.getInstance();
        ActorProxy hello = actors.spawnActor("name", new EncryptionDecorator(new HelloWorldActor()));
        hello.send(new Message(null, "It's safe 'cause its ciphered."));
        hello.send(new QuitMessage(null, "Quitting hello."));

        ActorProxy hello2 = actors.spawnActor("hello2", new FirewallDecorator(new HelloWorldActor()));
        hello2.send(new Message(hello, "Hi, I'm hello and I'm in the list."));
        hello2.send(new Message(new HelloWorldActor(), "Hi, I'm not in the list."));


        ActorProxy hello3 = actors.spawnActor("hello3", new FirewallDecorator(new EncryptionDecorator(new HelloWorldActor())));
        hello3.send(new Message(null, "Hi"));
        hello3.getActor().send(new Message(hello2.getActor(), "This is confidential."));
    }
}

