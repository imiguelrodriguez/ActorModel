package actors;

import messages.Message;

import java.util.LinkedList;
import java.util.Queue;

public class ActorProxy implements Actor {
    private ActorImp actor;
    private Queue<Message> queue = new LinkedList<>();
    public ActorProxy (Actor actor) {
        this.actor = (ActorImp) actor;
        Thread th = new Thread(this.actor);
        th.start();
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

    public ActorImp getActor() {
        return this.actor;
    }
}
