package messages;
import actors.*;
public class Message {
    private ActorImp from;
    private String text;

    public Message (ActorImp from, String text) {
        this.from = from;
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
