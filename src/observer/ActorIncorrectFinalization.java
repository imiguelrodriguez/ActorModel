package observer;

public class ActorIncorrectFinalization implements ActorEvent{
    @Override
    public String getInformation() {
        return "incorrectEndingActor";
    }
}
