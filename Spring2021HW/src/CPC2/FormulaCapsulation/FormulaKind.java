package CPC2.FormulaCapsulation;

/**
 * 该类表示的是逻辑表达式的种类。
 */
public enum FormulaKind {
    /**
     * 原子逻辑表达式
     */
    ATOMIC,
    /**
     * 二元逻辑表达式
     */
    BINARY,
    /**
     * 单元逻辑表达式
     */
    UNIT,
    /**
     * 封装过的【未知】逻辑表达式。
     */
    CAPSULATION;
}
