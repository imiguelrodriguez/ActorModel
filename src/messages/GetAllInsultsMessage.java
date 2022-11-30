package messages;
import actors.*;
public class GetAllInsultsMessage extends Message {
    public GetAllInsultsMessage(ActorImp from, String message) {
        super(from, message);
    }
}
