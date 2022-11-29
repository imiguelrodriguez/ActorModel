import java.util.LinkedList;
import java.util.Queue;

public class ActorImp implements Actor{
    private Queue<Message> mailbox = new LinkedList<>();
    private Message state;

    public ActorImp() {
        this.state = null;
    }

    @Override
    public void send(Message message) {
        this.mailbox.add(message);
    }
}
