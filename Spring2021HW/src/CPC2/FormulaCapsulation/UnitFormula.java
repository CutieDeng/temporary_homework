package CPC2.FormulaCapsulation;

/**
 * 单元运算逻辑表达式，实际上只有否定这一个，所以否定运算符会被默认初始化掉，不需要额外输入。
 */
public class UnitFormula extends Formula{
    private static final FormulaKind KIND_DEFAULT = FormulaKind.UNIT;
    {
        kind = KIND_DEFAULT;
    }

    private final FormulaDecorator decorator;
    private final Operation operation = Operation.NOT;

    /**
     * 包内可见的构造器，允许构造出一个单元运算表达式，请不要让逻辑表达式裸露！
     * @param decorator 输入一个逻辑表达式，形成这个表达式的否定表达式！
     */
    UnitFormula(FormulaDecorator decorator){
        this.decorator = decorator;
    }

    @Override
    public String toString(){
        return String.format("[%s %s]", operation, decorator);
    }
}
