package CPC2.FormulaCapsulation;

/**
 * 裸的二元逻辑表达式
 */
public class BinaryFormula extends Formula{

    private static final FormulaKind KIND_DEFAULT = FormulaKind.BINARY;

    {
        kind = KIND_DEFAULT;
    }

    final Operation operation;
    final FormulaDecorator major;
    final FormulaDecorator minor;

    /**
     * 包内可以使用的构造器，可以构造二元运算本身。
     * @param operation
     * @param major
     * @param minor
     */
    BinaryFormula(Operation operation, FormulaDecorator major, FormulaDecorator minor) {
        this.operation = operation;
        this.major = major;
        this.minor = minor;
    }

    @Override
    public String toString(){
        return String.format("[%s %s %s]", major, operation, minor);
    }
}
