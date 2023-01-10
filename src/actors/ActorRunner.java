package actors;

public class ActorRunner implements Runnable {
    private Actor actor;

    public ActorRunner(Actor actor) {
        this.actor = actor;
    }

    @Override
    public void run() {
        ((ActorImp)actor).actorLoop();
    }
}
