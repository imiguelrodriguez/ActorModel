package tests;

import actors.Actor;
import actors.ActorContext;
import actors.ActorProxy;
import actors.RingActor;
import messages.Message;
import observer.ActorListener;
import observer.MonitorService;
import observer.Traffic;

import java.util.ArrayList;
import java.util.HashMap;


public class ObserverTest {
    public static void main(String[] args) throws InterruptedException {
        int MAX = 100;
        ActorContext context = ActorContext.getInstance();
        RingActor [] actors = new RingActor[MAX];
        MonitorService monitorService = MonitorService.getInstance();
        monitorService.subscribe(new ActorListener());

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
        first.send(new Message(null, "Hi there!"));
        last.receive();

        HashMap<Traffic, ArrayList<Actor>> messagesTraffic;
        messagesTraffic = monitorService.getTraffic();
        System.out.println(messagesTraffic.toString());

        HashMap<Actor, ArrayList<Message>> sentMessages;
        sentMessages = monitorService.getSentMessages(first.getActor());
        System.out.println(sentMessages.toString());

        HashMap<Actor, ArrayList<Message>> receivedMessages;
        receivedMessages = monitorService.getReceivedMessages(first.getActor());
        System.out.println(receivedMessages.toString());

        System.out.println("");
        int nMess = monitorService.getNumberOfMessages(first.getActor());
        System.out.println("The number of messages sent by the actor " + first.getActor().getName() + " is "+ nMess);
    }
}
