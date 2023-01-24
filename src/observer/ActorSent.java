package observer;

public class ActorSent implements ActorEvent{
    @Override
    public String getInformation() {
        return "messageSent";
    }
}
