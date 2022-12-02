package actors;

public class ActorDecorator extends ActorImp {
    private ActorImp actor;

    public ActorDecorator(ActorImp actor) {
        super();
        this.actor = actor;
    }
}
