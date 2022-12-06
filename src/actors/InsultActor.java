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
    protected void process(Message message) {
        if(message!=null) {
        switch (message) {
            case GetInsultMessage m1 -> {
                System.out.println("GetInsultMessage");
                Random rand = new Random();
                InsultMessage randomInsult = this.insults.get(rand.nextInt(this.insults.size()));
                ((ActorProxy) m1.getFrom()).getQueue().add(randomInsult);
            }
            case AddInsultMessage m2 -> {
                System.out.println("AddInsultMessage");
                this.insults.add(new InsultMessage((ActorImp) m2.getFrom(), m2.getText()));

            }
            case GetAllInsultsMessage m3 -> {
                System.out.println("GetAllInsultsMessage");
                for (InsultMessage insult : this.insults)
                    ((ActorProxy) m3.getFrom()).getQueue().add(insult);
            }
            default -> System.out.println("Message not accepted");
        }
    }
    }
}
