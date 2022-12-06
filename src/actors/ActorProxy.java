package actors;

import messages.Message;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ActorProxy implements Actor {
    private ActorImp actor;
    private BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
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
        try {
            return queue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public ActorImp getActor() {
        return this.actor;
    }

    public Queue<Message> getQueue() {
        return queue;
    }
}
