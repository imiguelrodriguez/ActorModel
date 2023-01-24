package tests;

import actors.ActorContext;
import actors.ActorProxy;
import actors.RingActor;
import messages.Message;

public class RingActorsTest {
    static long iniTime;
    public static void main(String[] args) {
        int MAX = 10000;

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

        iniTime = System.currentTimeMillis();
        actors[0].send(new Message(null, "Broken telephone."));


    }

    public static long getIniTime() {
        return iniTime;
    }
}
