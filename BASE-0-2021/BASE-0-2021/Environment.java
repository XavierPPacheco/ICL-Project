import java.util.HashMap;
import java.util.Map;

public class Environment {

    Environment ancestor;
    Map<String, Integer> e;

    public Environment() {
        ancestor = null;
        e = new HashMap<>();
    }

    Environment(Environment ancestor) {
        this.ancestor = ancestor;
        e = new HashMap<>();
    }

    Environment	beginScope() {
        return new Environment(this);
    }

    Environment	endScope() {
        return ancestor;
    }

    void assoc(String id, int val) {
        if (e.containsKey(id))
            throw new IDDeclaredTwice();
        e.put(id, val);
    }

    int	find(String	id) {
        if (!e.containsKey(id))
            throw new UndeclaredID();
        return e.get(id);
    }

}
