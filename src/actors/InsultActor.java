package actors;

import messages.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InsultActor extends ActorImp {
    List<InsultMessage> insults = new ArrayList<>();
    public InsultActor() {
        this.insults.add(new InsultMessage(null, "Silly"));
        this.insults.add(new InsultMessage(null, "Dumb"));
        this.insults.add(new InsultMessage(null, "Dickhead"));
    }

    @Override
    public void send(Message message) {
        switch (message) {
            case GetInsultMessage m1 -> {
                System.out.println("GetInsultMessage");
                super.send(message);
            }
            case AddInsultMessage m2 -> {
                System.out.println("AddInsultMessage");
                super.send(message);
            }
            case GetAllInsultsMessage m3 -> {
                System.out.println("GetAllInsultsMessage");
                super.send(message);
            }
            default -> System.out.println("Message not accepted");
        }
    }
}
