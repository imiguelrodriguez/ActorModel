package actors;

import messages.Message;
import messages.QuitMessage;
import observer.ActorSent;
import tests.RingActorsTest;

public class RingActor extends ActorImp {
    private RingActor nextActor;
    private ActorProxy ap = null;
    private boolean last;
    public RingActor(boolean last) {
        super();
        this.last = last;
    }

    @Override
    public void send(Message message) {
        if(message.getFrom()!=null) monitorService.setEvent(new ActorSent(), message.getFrom(), message);
        message.setFrom(this);
        this.mailbox.add(message);
    }

    @Override
    public void process(Message message) {
        super.process(message);
        System.out.println("Actor" + this.getName());
            if(!last) {
                System.out.println("Sending to Actor" + nextActor.getName());
                nextActor.send(message);
            }
            else {
                System.out.println("Message arrived to last actor.");
                this.ap.getQueue().add(new QuitMessage(null, "QUIT"));
            }
    }
    public void setAp(ActorProxy ap) {
        this.ap = ap;
    }
    public RingActor getNext() {
        return this.nextActor;
    }
    public void setNext(RingActor next) {
        this.nextActor = next;
    }

    public boolean isLast() {
        return last;
    }
}
