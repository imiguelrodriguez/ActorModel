import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ActorContext {
    private Map<String, Actor> actors = new HashMap<>();

    public ActorProxy spawnActor(String name, Actor actor) {
        return null;
    }

    public ActorProxy lookup(String name) {
        return null;
    }

    public Set<String> getNames() {
        return actors.keySet();
    }
}
