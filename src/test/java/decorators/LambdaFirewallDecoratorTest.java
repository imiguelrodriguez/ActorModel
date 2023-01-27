package decorators;

import actors.ActorContext;
import actors.ActorImp;
import actors.ActorProxy;
import actors.HelloWorldActor;
import messages.AddClosureMessage;
import messages.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LambdaFirewallDecoratorTest {

    @Test
    void send() {
        ActorContext context = ActorContext.getInstance();
        ActorProxy ap = context.spawnActor("Actor1", new LambdaFirewallDecorator(new HelloWorldActor()));
        Message m = new AddClosureMessage(null, a->a.emptyMessage());
        int previousSize = ((LambdaFirewallDecorator)ap.getActor()).getPredicateList().size();
        ap.send(m);
        assert previousSize < ((LambdaFirewallDecorator)ap.getActor()).getPredicateList().size();
    }
}