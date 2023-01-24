package decorators;

import actors.Actor;
import actors.ActorContext;
import actors.ActorImp;
import actors.ActorProxy;
import messages.Message;

public class FirewallDecorator extends ActorImp {
    private ActorImp actor;
    public FirewallDecorator(ActorImp actor) {
        super(actor.getName());
        this.actor = actor;
    }

    @Override
    public void process(Message message) {
        Actor a = ActorContext.getInstance().lookup(((ActorProxy) message.getFrom()).getActor().getName());
        if(a!=null)
         this.actor.process(message);
        else System.out.println("That actor is not in the list!");
    }

    @Override
    public void send(Message message) {
        if(message.getFrom() instanceof ActorProxy)
            System.out.println("FIREWALL: proxies aren't allowed to communicate with actors.");
        else{
            this.mailbox.add(message);
            actor.send(message);
        }
    }
}
