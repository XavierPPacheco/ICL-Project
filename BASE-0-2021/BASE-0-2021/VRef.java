public class VRef implements  IValue{

    IValue value;

    public VRef(IValue value) {
        this.value = value;
    }

    public IValue getval() {
        return value;
    }

    public String toString() {
        return value.toString();
    }

}
