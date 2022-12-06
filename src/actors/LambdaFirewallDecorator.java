package actors;

public class LambdaFirewallDecorator extends ActorImp {
    private ActorImp actor;
    public LambdaFirewallDecorator(ActorImp actor) {
        this.actor = actor;
    }
}
