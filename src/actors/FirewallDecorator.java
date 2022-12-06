package actors;

import messages.Message;

public class FirewallDecorator extends ActorImp {
    private ActorImp actor;
    public FirewallDecorator(ActorImp actor) {
        this.actor = actor;
    }

    @Override
    public void process(Message message) {
        super.process(message);
    }

    @Override
    public void send(Message message) {
        super.send(message);
    }
}
