package actors;

import messages.Message;
import messages.QuitMessage;
import observer.ActorFinalization;
import observer.ActorReceived;
import observer.ActorSent;
import observer.MonitorService;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ActorImp implements Actor {
    protected BlockingQueue<Message> mailbox = new LinkedBlockingQueue<>();
    private Message state;
    private String name;
    private MonitorService monitorService = MonitorService.getInstance();

    public ActorImp(String name) {
        this.state = null;
        this.name = name;
    }
    public ActorImp() {
        this.state = null;
    }

    public void actorLoop() {
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
        monitorService.setEvent(new ActorFinalization(), this);
        System.out.println("Killing process...");
    }

    public void process(Message message) {
        monitorService.setEvent(new ActorReceived(), this, message);
        if(message!=null) {
            if(message.getFrom()!=null)
                System.out.print(message.getFrom()+" ");
            System.out.println(message.getText());
        }
    }

    @Override
    public void send(Message message) {
        monitorService.setEvent(new ActorSent(), this, message);
        this.mailbox.add(message);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MonitorService getMonitorService() {
        return monitorService;
    }

    public BlockingQueue<Message> getMailbox() {
        return mailbox;
    }
}
