package messages;

import actors.ActorImp;

public class AddClosureMessage extends Message {
    public AddClosureMessage(ActorImp from, String text) {
        super(from, text);
    }
}
