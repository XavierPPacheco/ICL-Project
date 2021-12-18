import java.util.HashMap;
import java.util.Map;

public class Environment<IValue> {

    Environment<IValue> ancestor;
    Map<String, IValue> e;

    public Environment() {
        ancestor = null;
        e = new HashMap<>();
    }

    Environment(Environment<IValue> ancestor) {
        this.ancestor = ancestor;
        e = new HashMap<>();
    }

    Environment	beginScope() {
        return new Environment<IValue>(this);
    }

    Environment	endScope() {
        return ancestor;
    }

    void assoc(String id, IValue val) {
        e.put(id, val);
    }

    IValue find(String id) {
        if (!e.containsKey(id))
            return ancestor.find(id);
        return e.get(id);
    }

}
