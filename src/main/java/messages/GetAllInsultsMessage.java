package messages;
import actors.*;
public class GetAllInsultsMessage extends Message {
    public GetAllInsultsMessage(Actor from) {
        super(from, "");
    }
}
