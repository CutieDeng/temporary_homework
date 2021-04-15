package CPC2.FormulaCapsulation;

/**
 * 原子的表达式，占据一个固定的内存空间，便于比较各表达式是否相同。
 * 没有公开的构造器，请不要企图构造这样一个表达式。
 */
public class AtomicFormula extends Formula{

    private static final FormulaKind KIND_DEFAULT = FormulaKind.ATOMIC;
    {
        kind = KIND_DEFAULT;
    }

    AtomicFormula() {
    }

    @Override
    public String toString(){
        return "" + id;
    }

}
