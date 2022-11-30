package actors;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ActorContext {
    private Map<String, Actor> actors = new HashMap<>();
    private static ActorContext instance;

    private ActorContext () {
    }

    public static ActorContext getInstance() {
        if(instance == null)
            instance = new ActorContext();
        return instance;
    }

    public ActorProxy spawnActor(String name, Actor actor) {
        if(!actors.containsKey(name))
            actors.put(name, actor);
        return new ActorProxy();
    }

    public ActorProxy lookup(String name) {
        return (ActorProxy) actors.get(name);
    }

    public Set<String> getNames() {
        return actors.keySet();
    }
}
