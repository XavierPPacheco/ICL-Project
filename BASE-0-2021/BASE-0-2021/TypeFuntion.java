
import java.util.List;

public class TypeFuntion implements IType{

    List<IType> parameterTypes;
    IType returnType;

    public TypeFuntion(List<IType> parameterTypes, IType bodyType) {
        this.parameterTypes = parameterTypes;
        this.returnType = bodyType;
    }

    public String toString() {
        return "fun_" + returnType.toString();
    }

    public IType getreftype() {
        return returnType;
    }

    public List<IType> getParameterTypes() {
        return parameterTypes;
    }

    public IType getReturnType() {
        return returnType;
    }


}
