package tests;

import actors.*;
import decorators.EncryptionDecorator;
import decorators.FirewallDecorator;
import decorators.LambdaFirewallDecorator;
import messages.*;


public class DecoratorTest {
    public static void main(String[] args) {
        ActorContext actors = ActorContext.getInstance();

        // ENCRYPTION DECORATOR
        ActorProxy hello = actors.spawnActor("hello", new EncryptionDecorator(new HelloWorldActor()));
        hello.send(new Message(null, "It's safe 'cause its ciphered."));
        //hello.send(new QuitMessage(null, "Quitting hello."));

        // FIREWALL DECORATOR
        ActorProxy hello2 = actors.spawnActor("hello2", new FirewallDecorator(new HelloWorldActor()));
        hello2.send(new Message(hello, "Hi, I'm a proxy!")); // we expect to get a Firewall message
        hello2.send(new Message(new HelloWorldActor(), "Hi, I'm not in the list."));
        hello2.send(new Message(hello.getActor(), "Hi, I'm actually in the list!"));

        // PIPELINE (FIREWALL + ENCRYPTION)
        ActorProxy hello3 = actors.spawnActor("hello3", new EncryptionDecorator(new FirewallDecorator(new HelloWorldActor())));
        hello3.send(new Message(null, "Hi"));
        hello3.send(new Message(hello2.getActor(), "This is confidential."));
        hello3.send(new Message(hello2, "This is confidencial but should be stopped by Firewall"));


        // LAMBDA FIREWALL DECORATOR
        ActorProxy hello4 = actors.spawnActor("hello4", new LambdaFirewallDecorator(new HelloWorldActor()));
        hello4.send(new AddClosureMessage(null, a -> a.emptyMessage()));
        hello4.send(new Message(null, ""));
        hello4.send(new Message(null, "Hello"));
    }
}

