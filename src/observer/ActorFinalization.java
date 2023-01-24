package observer;

public class ActorFinalization implements ActorEvent{
    @Override
    public String getInformation() {
        return "endingActor";
    }
}
