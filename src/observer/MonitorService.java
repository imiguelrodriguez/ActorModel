package observer;

import java.util.ArrayList;
import java.util.List;

// This would be the NotificationService which will have the list of the ActorListener
public class MonitorService  {
    private final List<ActorListener> listeners;
    public MonitorService() {
        listeners = new ArrayList<>();
    }

    public void subscribe(ActorListener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(ActorListener listener) {
        listeners.remove(listener);
    }

    public void notifySubscribers() {
        listeners.forEach(ActorListener::update);
    }
}
