package tests;

import actors.*;
import dynamic.*;
import messages.QuitMessage;

public class DynamicProxyTest {
    public static void main(String[] args) {

        ActorContext acContext = ActorContext.getInstance();

        ActorProxy actor = acContext.spawnActor("name",new InsultActor());

        InsultService insulter = (InsultService) DynamicProxy.intercept(new InsultServiceImp(), actor);
        insulter.addInsult("stupid");
        insulter.addInsult("idiot");
        System.out.println(insulter.getInsult());
        System.out.println(insulter.getAllInsults());
        actor.send(new QuitMessage(null, "QUIT"));
    }
}