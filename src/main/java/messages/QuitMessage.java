package messages;
import actors.*;
public class QuitMessage extends Message{
    public QuitMessage(ActorImp from, String message) {
        super(from, message);
    }
}
