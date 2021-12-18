public class VInt implements IValue {

    int v;

    VInt(int v0) {
        v = v0;
    }

    int getval() {
        return v;
    }

    public String toString() {
        return Integer.toString(v);
    }

}
