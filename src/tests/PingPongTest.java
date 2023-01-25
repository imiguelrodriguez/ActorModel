package tests;

import actors.ActorContext;
import actors.ActorProxy;
import actors.PingPongActor;
import messages.Message;
import messages.QuitMessage;

public class PingPongTest {
    public static void main(String[] args) {
        ActorContext context = ActorContext.getInstance();
        ActorProxy ping = context.spawnActor("PING", new PingPongActor(null));
        ActorProxy pong = context.spawnActor("PONG", new PingPongActor(ping));
        ((PingPongActor)ping.getActor()).setMate(pong);

        pong.send(new Message(ping, "This is better than tennis!"));
        ping.send(new Message(pong, "Absolutely!"));

        ping.send(new QuitMessage(null, ""));
        pong.send(new QuitMessage(null, ""));
    }
}
