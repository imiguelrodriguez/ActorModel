package actors;

import messages.Message;
import messages.QuitMessage;
import tests.RingActorsTest;

public class RingActor extends ActorImp {
    private RingActor nextActor;
    private boolean last;
    public RingActor(boolean last) {
        super();
        this.last = last;
    }

    @Override
    public void process(Message message) {
        super.process(message);
        if(!last) {

            nextActor.send(message);
        }
        else {
            long endTime = System.currentTimeMillis();
            System.out.println("Time passed " + (endTime - RingActorsTest.getIniTime()) + " ms");
            super.send(new QuitMessage(this, "QUIT"));
        }

    }

    public RingActor getNext() {
        return this.nextActor;
    }
    public void setNext(RingActor next) {
        this.nextActor = next;
    }
}
