package CPC;

public class UnitFormula {

    private Decorator decorator;

    UnitFormula(Decorator decorator) {
        this.decorator = decorator;
    }

    @Override public boolean equals(Object o){
        if (o == null){
            return false;
        }
        if (o.getClass() != this.getClass()){
            return false;
        }
        return decorator.equals(((UnitFormula) o).decorator);
    }
}
