package observer;

import actors.Actor;
import actors.ActorContext;
import actors.ActorImp;
import messages.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

// This would be the NotificationService which will have the list of the ActorListener
public class MonitorService  {
    private static MonitorService instance;
    private List<ActorListener> listeners = new ArrayList<>();;
    private HashMap<Actor, ArrayList<Message>> mSent = new HashMap<>();
    private HashMap<Actor, ArrayList<Message>> mReceived = new HashMap<>();
    private HashMap<Events, ArrayList<Actor>> events = new HashMap<>();

    public MonitorService() {
    }

    public static MonitorService getInstance(){
        if(instance == null)
            instance = new MonitorService();
        return instance;
    }
    public void monitorActor(String name) {
        ActorContext context = ActorContext.getInstance();
        Actor actor = context.lookup(name);
        if(actor != null) {

        }
    }

    public void monitorAllActors(){
        ActorContext context = ActorContext.getInstance();

    }

    public int getNumberOfMessages(Actor a) {
        return this.mSent.get(a).size(); //also with (int) mSent.get(a).stream().count()
    }

    public void setEvent(ActorEvent event, Actor actor, Message msg) {
        switch (event) {
            case ActorSent e -> {
                ArrayList<Message> list = mReceived.get(actor);
                if (list == null) list = new ArrayList<>();
                list.add(msg);
                mReceived.put(actor, list);
            }

            case ActorReceived e -> {
                ArrayList<Message> list = mSent.get(actor);
                if (list == null) list = new ArrayList<Message>();
                list.add(msg);
                mSent.put(actor, list);
            }
            default -> throw new IllegalStateException("Unexpected value: " + event);
        }

        notifySubscribers(event);
    }

    public HashMap<Actor, ArrayList<Message>> getSentMessages(Actor actor) {
        HashMap<Actor, ArrayList<Message>> dict = new HashMap<>();
        ArrayList<Message> list = this.mSent.get(actor);
        dict.put(actor, list);
        return dict;
    }

    public HashMap<Actor, ArrayList<Message>> getReceivedMessages(Actor actor) {
        HashMap<Actor, ArrayList<Message>> dict = new HashMap<>();
        ArrayList<Message> list = this.mReceived.get(actor);
        dict.put(actor, list);
        return dict;
    }


    public void subscribe(ActorListener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(ActorListener listener) {
        listeners.remove(listener);
    }

    public void notifySubscribers(ActorEvent event) {
        listeners.forEach(listener ->listener.update(event));
    }

    public HashMap<Traffic, ArrayList<Actor>> getTraffic() {
        HashMap<Traffic, ArrayList<Actor>> dict = new HashMap<>();
        // initialize dictionary with Traffic enum keys
        dict.put(Traffic.LOW, null);
        dict.put(Traffic.MEDIUM, null);
        dict.put(Traffic.HIGH, null);

        Set<Actor> keys = this.mSent.keySet();
        int size = 0;
        Traffic traffic = null;

        for (Actor key : keys) {

            size = this.mSent.get(key).size();

            if (size < 5) traffic = Traffic.LOW;
            else if (size < 15) traffic = Traffic.MEDIUM;
            else traffic = Traffic.HIGH;


            ArrayList<Actor> list = dict.get(traffic);

            if (list == null) list = new ArrayList<>();
            list.add(key);
            dict.put(traffic, list);
        }
        return dict;
    }

    public void setEvent(ActorEvent event, Actor actor) {

    }

    public HashMap<Events, ActorEvent> getEvents() {

        return null;
    }
}
