package actors;

import messages.Message;

public class PingPongActor extends ActorImp{
    private Actor mate;
    public PingPongActor(Actor mate) {
        super();
        this.mate = mate;
    }

    public Actor getMate() {
        return mate;
    }

    public void setMate(Actor mate) {
        this.mate = mate;
    }

    @Override
    public void process(Message message) {
        System.out.println("I'm " + this.getName() + ". Processing message from " + ((ActorProxy)message.getFrom()).getActor().getName());
        System.out.println("Message: " + message.getText());

    }
}
