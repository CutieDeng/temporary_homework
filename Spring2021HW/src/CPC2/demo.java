package CPC2;

import CPC2.FormulaCapsulation.FormulaDecorator;

public class demo {
    public static void main(String[] args) {
        // 创建 atomic formula A
        FormulaDecorator decorator = FormulaDecorator.createAtomicFormula("A");
        // 创建 formula A -> A
        FormulaDecorator decorator1 = new FormulaDecorator(decorator, decorator);
        // 输出测试
        System.out.println(decorator1);
        // 创建不确定逻辑表达式 P1, P2
        FormulaDecorator decorator2 = FormulaDecorator.createAccessibleFormula("P1");
        FormulaDecorator decorator3 = FormulaDecorator.createAccessibleFormula("P2");
        // 输出测试
        System.out.println(decorator2);        System.out.println(decorator3);
        // 构建逻辑表达式 P1 -> P2
        FormulaDecorator decorator4 = new FormulaDecorator(decorator2, decorator3);
        System.out.println("----------------------");
        //尝试让 P1 -> P2 匹配 A -> A
        System.out.println(decorator4);
        decorator4.match(decorator1);
        //输出不确定逻辑表达式 P1, P2
        System.out.println(decorator2);        System.out.println(decorator3);
    }
}
