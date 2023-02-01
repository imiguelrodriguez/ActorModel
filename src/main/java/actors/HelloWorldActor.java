package actors;

import messages.Message;

public class HelloWorldActor extends ActorImp {

    public HelloWorldActor() {
        super();
    }

    @Override
    public void process(Message message) {
        super.process(message);
        if(message!=null) {
            System.out.print("Hello World Actor: ");
            System.out.println(message.getText() + " from " + message.getFrom());
        }
    }
}
