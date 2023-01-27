package tests;

import actors.*;
import messages.*;

import java.util.ArrayList;
import java.util.List;

public class ProxyTest {
    public static void main(String[] args) {
        ActorContext actors = ActorContext.getInstance();
        ActorProxy aProxy1 = actors.spawnActor("actor1", new InsultActor());
        ActorProxy aProxy3 = actors.spawnActor("actor3", new HelloWorldActor());
        aProxy1.send(new GetInsultMessage(aProxy1));
        Message m = null;
        try {
            m = aProxy1.receive();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(m.getText());
        aProxy3.send(new Message(null, "First message , hello!"));
        aProxy1.send(new AddInsultMessage("Motherfucker"));
        aProxy1.send(new GetAllInsultsMessage(aProxy1));
        List<Message> messages = new ArrayList<>();
        do {
            try {
                messages.add(aProxy1.receive());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        while(!aProxy1.getQueue().isEmpty());

        for(Message me: messages)
            System.out.println(me.getText());

        aProxy1.send(new QuitMessage(null, "You're quitting proxy 1."));
        aProxy3.send(new QuitMessage(null, "You're quitting proxy 3."));

    }
}
