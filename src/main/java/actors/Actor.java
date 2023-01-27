package actors;

import messages.Message;

public interface Actor {
    void send(Message message);
}
