package messages;
import actors.*;
public class Message {
    private Actor from;
    private String text;

    public Message (Actor from, String text) {
        this.from = from;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Actor getFrom() {
        return this.from;
    }
}
