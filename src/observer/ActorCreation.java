package observer;

public class ActorCreation implements ActorEvent{
    @Override
    public String getInformation() {
        return "creatingActor";
    }
}
