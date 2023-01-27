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
        if(message instanceof AddClosureMessage) {
            this.predicateList.add(((AddClosureMessage) message).getPredicate());
            System.out.println("Added closure " + ((AddClosureMessage) message).getPredicate());
        }
        else { // check if the message fulfills the requirements
            for(Predicate<Message> predicate : this.predicateList) {
                if(predicate.test(message)) {
                    System.out.println("Message " + message.getText() + " fulfills the lambda conditions.");
                    //this.mailbox.add(message);
                    actor.send(message);
                    break;
                }
                else System.out.println("Message " + message.getText() + " does not fulfill the lambda conditions.");
            }
        }
    }

    public List<Predicate<Message>> getPredicateList() {
        return predicateList;
    }
}
