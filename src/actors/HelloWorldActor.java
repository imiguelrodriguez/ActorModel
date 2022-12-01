package actors;

import messages.Message;

public class HelloWorldActor extends ActorImp {

    public HelloWorldActor() {
        super();
    }

    @Override
    protected void process(Message message) {
        if(message!=null) {
            System.out.print("Hello World Actor: ");
            System.out.println(message.getText());
        }
    }
}
