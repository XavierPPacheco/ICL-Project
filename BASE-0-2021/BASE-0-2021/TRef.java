public class TRef implements IType{
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TRef other = (TRef) obj;
        if (ref == null) {
            if (other.ref != null)
                return false;
        } else if (!ref.equals(other.ref))
            return false;
        return true;
    }

    IType ref;

    public TRef(IType r) {
        ref = r;
    }

    public String toString() {
        return "ref_" + ref.toString();
    }

    public IType getreftype() {
        return ref;
    }

}
