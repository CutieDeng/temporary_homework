package CML;

import java.util.ArrayList;
import java.util.List;

public class hw6_3 {

    private static List<logic_expression> atomicLetter = new ArrayList<>();

    private static logic_expression.roll roll = new logic_expression.roll();

    public static void main(String[] args) {
        logic_expression A = new logic_expression("A");
        logic_expression B = new logic_expression("B");
        logic_expression C = new logic_expression("C");
        logic_expression D = new logic_expression("D");
        atomicLetter.add(A);
        System.out.println("(a):");
        roll.setExpressions(atomicLetter);
        roll.dfs( () -> {
            System.out.println(new logic_expression(logic_calc.OR, A, new logic_expression(logic_calc.NOT, A)));
        });
        System.out.println("(b):");
        roll.dfs( () -> {
            System.out.println(new logic_expression(logic_calc.AND, A, new logic_expression(logic_calc.NOT, A)));
        });
        System.out.println("(c):");
        atomicLetter.add(B);
        roll.dfs( () -> {
            System.out.println(new logic_expression(logic_calc.OR, new logic_expression(logic_calc.NOT, A), B));
        });
        System.out.println("(d):");
        roll.dfs( () -> {
            System.out.println(new logic_expression(logic_calc.AND,
                    new logic_expression(logic_calc.OR, A, B),
                    new logic_expression(logic_calc.NOT,
                            new logic_expression(logic_calc.AND, A, B))));
        });
        System.out.println("(e):");
        roll.dfs( () -> {
            System.out.println(new logic_expression(logic_calc.EQUAL,
                    new logic_expression(logic_calc.OR,
                            new logic_expression(logic_calc.NOT, A),
                            new logic_expression(logic_calc.NOT, B)),
                    new logic_expression(logic_calc.AND, A, B)));
        });
        System.out.println("(f):");
        atomicLetter.add(C);
        roll.dfs( () -> {
            System.out.println(new logic_expression(logic_calc.IMPLY,
                    A,
                    new logic_expression(logic_calc.OR,
                            B,
                            new logic_expression(logic_calc.NOT, C))));
        });
        System.out.println("(g):");
        atomicLetter.add(D);
        roll.dfs( () -> {
            System.out.println(new logic_expression(logic_calc.IMPLY,
                    new logic_expression(logic_calc.AND,
                            new logic_expression(logic_calc.AND, A, B),
                            new logic_expression(logic_calc.AND, C, D)
                            ),
                    A));
        });
        System.out.println("(h):");
        atomicLetter.remove(D);
        roll.dfs( () -> {
            System.out.println(new logic_expression(logic_calc.IMPLY,
                    new logic_expression(logic_calc.EQUAL,
                            A,
                            new logic_expression(logic_calc.OR,
                                    new logic_expression(logic_calc.NOT, B),
                                    C)
                    ),
                    new logic_expression(logic_calc.IMPLY,
                            new logic_expression(logic_calc.NOT, A),
                            B)));
        });
    }
}
