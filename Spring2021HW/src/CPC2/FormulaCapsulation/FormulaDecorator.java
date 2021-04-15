package CPC2.FormulaCapsulation;

import java.util.HashSet;
import java.util.Set;

public class FormulaDecorator extends Formula{

    private static final Set<String> names = new HashSet<>(10);
    private static final FormulaKind KIND_DEFAULT = FormulaKind.CAPSULATION;

    {
        kind = KIND_DEFAULT;
    }

    private final String name;
    private Formula value;
    private Changeable permit;

    private FormulaDecorator(){
        this((String) null);
    }

    private FormulaDecorator(String name){
        if (name != null && !names.add(name)){
            throw new RuntimeException("");
        }
        this.name = name;
    }

    /**
     * 创建自由逻辑表达式，它里面并没有值，但是可以自动模糊匹配被赋值。
     * @param name 自由逻辑表达式名字
     * @return 一个自由的逻辑表达式。
     */
    public static FormulaDecorator createAccessibleFormula(String name){
        FormulaDecorator decorator = new FormulaDecorator(name);
        decorator.permit = Changeable.ALWAYS;
        return decorator;
    }

    /**
     * 创建原子逻辑表达式，该逻辑表达式不允许被修改，且唯一。
     * @param name 原子逻辑表达式的名字。
     * @return 逻辑表达式本身。
     */
    public static FormulaDecorator createAtomicFormula(String name){
        FormulaDecorator decorator = new FormulaDecorator(name);
        decorator.permit = Changeable.IMPOSSIBLE;
        decorator.value = new AtomicFormula();
        return decorator;
    }

    /**
     * 一元逻辑表达式的构建，即否定一个逻辑表达式。【default】
     * @param decorator 对该逻辑表达式进行否定。
     */
    @SuppressWarnings("CopyConstructorMissesField")
    public FormulaDecorator(FormulaDecorator decorator){
        this();
        this.value = new UnitFormula(decorator);
    }

    /**
     * 构建二元逻辑表达式。
     * @param operation  二元运算符
     * @param decorator1 逻辑表达式1
     * @param decorator2 逻辑表达式2
     */
    public FormulaDecorator(Operation operation, FormulaDecorator decorator1, FormulaDecorator decorator2){
        this();
        this.value = new BinaryFormula(operation, decorator1, decorator2);
    }

    /**
     * 构造二元实质蕴涵逻辑表达式。
     * @param decorator1 逻辑表达式1
     * @param decorator2 逻辑表达式2
     */
    public FormulaDecorator(FormulaDecorator decorator1, FormulaDecorator decorator2){
        this(Operation.IMPLY, decorator1, decorator2);
    }

    /**
     * 该方法会严格判定两个逻辑表达式是否完全一样，而不是等价类。
     * @param other 另一个逻辑表达式
     * @return 如果两个逻辑表达式完全相同，则返回真值。
     */
    @Override
    public boolean equals(Object other){
         if (!(other instanceof FormulaDecorator)){
             return false;
         }
         return getValue().equals(((FormulaDecorator) other).getValue());
    }

    /**
     * 这个方法会企图找到没有包装的逻辑表达式，并将它返回。
     * @return 裸露的逻辑表达式。
     */
    Formula getValue(){
        if (this.value instanceof FormulaDecorator){
            return ((FormulaDecorator) this.value).getValue();
        }
        else{
            return this.value;
        }
    }

    /**
     * 使该逻辑表达式时刻与另一个逻辑表达式相同！
     * @return 如果该请求被拒绝，则返回假值。
     */
    public boolean relyOn(FormulaDecorator decorator){
        if (permit == Changeable.ALWAYS) {
            permit = Changeable.TEMPORARY;
            this.value = decorator;
            return true;
        }
        return false;
    }

    /**
     * 释放该逻辑表达式对其他逻辑表达式的依赖。
     * @return 释放成功则返回真。
     */
    public boolean release(){
       if (permit == Changeable.TEMPORARY){
           permit = Changeable.ALWAYS;
           this.value = null;
           return true;
       }
       return false;
    }

    /**
     * 输出逻辑表达式的内容。
     * @return 尝试输出逻辑表达式的内容。
     */
    @Override
    public String toString() {
        if (this.name == null){
            return this.value.toString();
        }
        else{
            if (this.value != null){
                return String.format("[%s = %s]", this.name, this.value);
            }
            else{
                return String.format("%s", this.name);
            }
        }
    }

    public boolean match(FormulaDecorator otherDecorator){
        if (this.permit == Changeable.ALWAYS){
            this.value = otherDecorator;
            this.permit = Changeable.TEMPORARY;
            return true;
        }
        Formula f1 = this.getValue();
        Formula f2 = otherDecorator.getValue();
        if (f1.getClass() != f2.getClass()){
            return false;
        }
        if (AtomicFormula.class.equals(f1.getClass()) ||
                UnitFormula.class.equals(f1.getClass())) {
            return f1.equals(f2);
        } else if (BinaryFormula.class.equals(f1.getClass())) {
            if (((BinaryFormula) f1).operation != ((BinaryFormula) f2).operation) {
                return false;
            }
            boolean t;
            t = ((BinaryFormula) f1).major.match(((BinaryFormula) f2).major) &&
                    ((BinaryFormula) f1).minor.match(((BinaryFormula) f2).minor);
            return t;
        }
        throw new RuntimeException();
    }
}
