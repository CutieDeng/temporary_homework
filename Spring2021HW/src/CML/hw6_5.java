package CML;

import java.util.ArrayList;
import java.util.List;

public class hw6_5 {

    private static List<logic_expression> atomicLetter = new ArrayList<>();

    private static logic_expression.roll roll = new logic_expression.roll();

    public static void main(String[] args) {
        logic_expression A = new logic_expression("A");
        logic_expression B = new logic_expression("B");
        logic_expression C = new logic_expression("C");
        logic_expression D = new logic_expression("D");
        atomicLetter.add(A);
        atomicLetter.add(B);
        atomicLetter.add(C);
        atomicLetter.add(D);
        roll.setExpressions(atomicLetter);
        if (true){
            roll.dfs( () -> {
                logic_expression e1 = new logic_expression(logic_calc.IMPLY, A, B);
                logic_expression e2 = new logic_expression(logic_calc.IMPLY, B, C);
                logic_expression e3 = new logic_expression(logic_calc.EQUAL,
                        new logic_expression(logic_calc.OR, C, D),
                        new logic_expression(logic_calc.NOT, B));
                System.out.printf("%s ||| %s ||| %s\n", e1, e2, e3);
                if (e1.getValue() && e2.getValue() && e3.getValue()){
                    System.out.println("*** " + atomicLetter);
                }
            });
        }
        System.out.println("\n");
        if (true){
            roll.dfs( () -> {
                logic_expression e4 = new logic_expression(logic_calc.NOT,
                        new logic_expression(logic_calc.OR,
                                new logic_expression(logic_calc.NOT, B),
                                A));
                logic_expression e5 = new logic_expression(logic_calc.OR,
                        A,
                        new logic_expression(logic_calc.NOT, C));
                logic_expression e6 = new logic_expression(logic_calc.IMPLY,
                        B,
                        new logic_expression(logic_calc.NOT, C));
                System.out.printf("%s ||| %s ||| %s\n", e4, e5, e6);
                if (e4.getValue() && e5.getValue() && e6.getValue()){
                    System.out.println("*** " + atomicLetter);
                }
            });
        }
        System.out.println("\n");
        if (true){
            roll.dfs( () -> {
                logic_expression e7 = new logic_expression(logic_calc.IMPLY, D, B);
                logic_expression e8 = new logic_expression(logic_calc.OR, A, new logic_expression(logic_calc.NOT, B));
                logic_expression e9 = new logic_expression(logic_calc.NOT,
                        new logic_expression(logic_calc.AND, D, A));
                logic_expression e10 = D;
                System.out.printf("%s ||| %s ||| %s ||| %s\n", e7, e8, e9, e10);
                if (e7.getValue() && e8.getValue() && e9.getValue() && e10.getValue()){
                    System.out.println("*** " + atomicLetter);
                }
            });
        }

    }
}
