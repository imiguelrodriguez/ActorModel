package messages;

import actors.ActorImp;

import java.util.function.Predicate;

public class AddClosureMessage extends Message {
    Predicate<Message> predicate;
    public AddClosureMessage(ActorImp from, Predicate<Message> predicate) {
        super(from, "");
        this.predicate = predicate;
    }

    public Predicate<Message> getPredicate() {
        return this.predicate;
    }
}
