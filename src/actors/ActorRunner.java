package actors;

import observer.ActorIncorrectFinalization;
import observer.MonitorService;

public class ActorRunner implements Runnable {
    private final Actor actor;

    public ActorRunner(Actor actor) {
        this.actor = actor;
    }

    @Override
    public void run() {
        MonitorService ms = ((ActorImp) actor).getMonitorService();
        try {
           ms.setEvent(new ActorIncorrectFinalization(), this.actor);
            ((ActorImp) actor).actorLoop();
        } catch (Exception e) {
            ms.setEvent(new ActorIncorrectFinalization(), this.actor );
            e.printStackTrace();
        }
    }
}
