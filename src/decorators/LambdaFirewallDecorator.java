package decorators;

import actors.ActorImp;
import messages.AddClosureMessage;
import messages.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class LambdaFirewallDecorator extends ActorImp {
    private ActorImp actor;
    private List<Predicate<Message>> predicateList = new ArrayList<>();
    public LambdaFirewallDecorator(ActorImp actor) {
        this.actor = actor;
    }

    @Override
    public void process(Message message) {
        this.actor.process(message);
    }

    @Override
    public void send(Message message) {
        if(message instanceof AddClosureMessage)
            this.predicateList.add(((AddClosureMessage)message).getPredicate());

    }
}
