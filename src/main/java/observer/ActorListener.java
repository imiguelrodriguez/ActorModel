package observer;


public class ActorListener  {

   public ActorListener() {}

   public void update(ActorEvent event) {
      System.out.println("New state: " + event.getInformation());
   }
}
