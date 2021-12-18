public class VBool implements IValue {

    boolean v;

    VBool(boolean v0) {
        v = v0;
    }

    boolean getval() {
        return v;
    }

    public String toString() {
        return Boolean.toString(v);
    }
}
