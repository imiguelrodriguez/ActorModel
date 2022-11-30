package actors;

import messages.Message;

import java.util.LinkedList;
import java.util.Queue;

public class ActorProxy implements Actor {
    private static ActorImp actor;
    private Queue<Message> queue = new LinkedList<>();
    public ActorProxy () {

    }

    @Override
    public void send(Message message) {
        if(actor == null) {
            actor = new ActorImp();
        }
        actor.send(message);
    }

    public Message receive() {
        return null;
    }
}
