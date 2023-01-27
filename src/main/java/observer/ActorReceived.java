package observer;

public class ActorReceived implements ActorEvent{
    @Override
    public String getInformation() {
        return "messageReceived";
    }
}
