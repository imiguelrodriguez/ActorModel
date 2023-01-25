package tests;

import actors.ActorContext;
import actors.ActorProxy;
import actors.RingActor;
import messages.Message;
import messages.QuitMessage;

import java.util.Set;

public class RingActorsTest {
    static long iniTime, endTime;
    public static void main(String[] args) throws InterruptedException {
        int MAX = 100;

        RingActor[] actors = new RingActor[MAX];
        ActorContext context = ActorContext.getInstance();
        // RING CREATION

        for(int i = 0; i < MAX; i++) {
          if(i!=MAX-1)
            actors[i] = new RingActor(false);
          else actors[i] = new RingActor(true);
          context.spawnActor(String.valueOf(i), actors[i]);
        }

        for(int i = 0; i < MAX; i++) {
            if(i < MAX - 1) actors[i].setNext(actors[i+1]);
            else actors[i].setNext(actors[0]);
        }

        ActorProxy first = new ActorProxy(context.lookup("0"));
        ActorProxy last = new ActorProxy(context.lookup((MAX-1) + ""));
        ((RingActor)last.getActor()).setAp(last);
        iniTime = System.currentTimeMillis();
        first.send(new Message(null, "Broken telephone."));
        last.receive();
        endTime = System.currentTimeMillis();

        Set<String> names = context.getNames();
        for(String name : names) {
            context.lookup(name).send(new QuitMessage(null, "QUIT"));
        }

        System.out.println("Total elapsed for " + MAX + " iterations is " + (endTime-iniTime) +  " ms.");

    }

}
