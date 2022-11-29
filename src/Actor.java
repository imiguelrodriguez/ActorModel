import java.util.LinkedList;
import java.util.Queue;

public class Actor {
    private Queue<Message> mailbox;
    private Message state;

    public Actor () {
        this.mailbox = new LinkedList<Message>();
        this.state = null;
    }
}
