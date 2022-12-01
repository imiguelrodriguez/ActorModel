package messages;

import actors.*;

public class InsultMessage extends Message {
    public InsultMessage(ActorImp from, String text) {
        super(from, text);
    }
}
