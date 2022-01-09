import java.util.HashMap;
import java.util.Map;

public class EnvironmentT {
    Map<String, IType> scope;
    EnvironmentT ancestor;

    public EnvironmentT() {
        ancestor = null;
        scope = new HashMap<>();
    }

    private EnvironmentT(EnvironmentT e) {
        scope = new HashMap<>();
        ancestor = e;
    }

    public EnvironmentT beginScope() {
        return new EnvironmentT(this);
    }

    public EnvironmentT endScope() {
        return ancestor;
    }

    public void assoc(String id, IType value) {
        this.scope.put(id, value);
    }

    public IType find(String id) {
        IType res = scope.get(id);
        if (res == null)
            return ancestor.find(id);
        else
            return res;
    }
}
