package actors;

import messages.Message;
import messages.QuitMessage;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ActorImp implements Actor, Runnable {
    private BlockingQueue<Message> mailbox = new LinkedBlockingQueue<>();
    private Message state;

    public ActorImp() {
        this.state = null;
    }

    private void actorLoop() {
        try {
            state = this.mailbox.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while(!(state instanceof QuitMessage)) {
            process(state);
            try {
                state = this.mailbox.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Killing process...");
    }

    public void process(Message message) {
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
