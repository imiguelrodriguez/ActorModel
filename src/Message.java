public class Message {
    private ActorImp from;
    private String message;

    public Message (ActorImp from, String message) {
        this.from = from;
        this.message = message;
    }
}
