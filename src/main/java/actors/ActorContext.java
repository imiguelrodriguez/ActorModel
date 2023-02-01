package actors;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ActorContext {
    private Map<String, Actor> actors = new HashMap<>();
    private static ActorContext instance;

    public static ActorContext getInstance() {
        if(instance == null)
            instance = new ActorContext();
        return instance;
    }

    public ActorProxy spawnActor(String name, Actor actor) {
        if(!actors.containsKey(name)) {
            ((ActorImp) actor).setName(name);
            actors.put(name, actor);
            Thread th = new Thread(new ActorRunner(actor));
            th.start();
        }
        return new ActorProxy(actor);
    }

    public Actor lookup(String name) {
        return actors.get(name);
    }

    public Set<String> getNames() {
        return actors.keySet();
    }
    public ArrayList<Actor> getAllActors() {
        return new ArrayList<>(this.actors.values().stream().toList());
    }

    public void removeActor(String name) {
        Actor a = lookup(name);
        if(a!=null)
            this.actors.remove(name);
    }
}
