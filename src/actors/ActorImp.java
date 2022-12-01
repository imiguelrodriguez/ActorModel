package actors;

import messages.Message;
import messages.QuitMessage;

import java.util.LinkedList;
import java.util.Queue;

public class ActorImp implements Actor, Runnable {
    private Queue<Message> mailbox = new LinkedList<>();
    private Message state;

    public ActorImp() {
        this.state = null;
    }

    private void actorLoop() {
        state = this.mailbox.poll();;
        while(!(state instanceof QuitMessage)) {
            process(state);
            state = this.mailbox.poll();
        }
        System.out.println("Killing process...");
    }

    protected void process(Message message) {
        if(message!=null) {
            if(message.getFrom()!=null)
                System.out.print(message.getFrom()+" ");
            System.out.println(message.getText());
        }
    }

    @Override
    public void send(Message message) {
        this.mailbox.add(message);
    }

    @Override
    public void run() {
        actorLoop();
    }
}
