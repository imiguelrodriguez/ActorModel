package decorators;

import actors.ActorImp;
import messages.Message;
import messages.QuitMessage;

public class EncryptionDecorator extends ActorImp {
    private ActorImp actor;
    public EncryptionDecorator(ActorImp actor) {
        super(actor.getName());
        this.actor = actor;
    }

    @Override
    public void send(Message message) {
        String encryptedString = EncryptionDecorator.encrypt(message.getText());
        Message encryptedMessage;
        if(message instanceof QuitMessage){
            encryptedMessage = new QuitMessage(this, encryptedString);
        }
        else encryptedMessage = new Message(this, encryptedString);

        this.mailbox.add(encryptedMessage);
        this.actor.send(encryptedMessage);
    }

    @Override
    public void process(Message message) {
        String decryptedString = EncryptionDecorator.decipher(message.getText());
        Message decryptedMessage = new Message(this, decryptedString);
        this.actor.process(decryptedMessage);
    }

    private static String encrypt(String message) {
        StringBuilder result = new StringBuilder();
        int len = message.length();

        for(int i = 0; i < len; i++){
            char c = (char)(message.charAt(i) + 3);
            if(c > 'z')
                result.append((char) (message.charAt(i)) - (26 - 3));
            else
                result.append((char) (message.charAt(i) + 3));
        }
        return result.toString();
    }

    private static String decipher(String message) {
        StringBuilder result = new StringBuilder();
        int len = message.length();
        for(int i = 0; i < len; i++){
            char c = (char)(message.charAt(i) - 3);
            if(c > 'z')
                result.append((char) (message.charAt(i)) + (26 + 3));
            else
                result.append((char) (message.charAt(i) - 3));
        }
        return result.toString();
    }
}
