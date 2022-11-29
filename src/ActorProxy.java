public class ActorProxy implements Actor {
    private static ActorImp actor;
    @Override
    public void send(Message message) {
        if(actor == null) {
            actor = new ActorImp();
        }
        actor.send(message);
    }
}
