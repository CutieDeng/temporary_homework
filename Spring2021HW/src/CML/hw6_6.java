package CML;

import java.util.ArrayList;
import java.util.List;

public class hw6_6 {

    private static List<logic_expression> atomicLetter = new ArrayList<>();

    private static logic_expression.roll roll = new logic_expression.roll();

    public static void main(String[] args) {
        logic_expression A = new logic_expression("A");
        logic_expression B = new logic_expression("B");
        logic_expression C = new logic_expression("C");

        atomicLetter.add(A);
        atomicLetter.add(B);

        roll.setExpressions(atomicLetter);
        System.out.println("(a)");
        roll.dfs(()->{
            System.out.println(new logic_expression(logic_calc.IMPLY,
                    new logic_expression(logic_calc.AND,
                            new logic_expression(logic_calc.IMPLY, A, B),
                            A),
                    B));
        });
        System.out.println("(b)");
        roll.dfs(()->{
            System.out.println(new logic_expression(logic_calc.IMPLY,
                    new logic_expression(logic_calc.AND,
                            new logic_expression(logic_calc.IMPLY, A, B),
                            B),
                    A));
        });
        System.out.println("(c)");
        roll.dfs(()->{
            System.out.println(new logic_expression(logic_calc.IMPLY,
                    new logic_expression(logic_calc.AND,
                            new logic_expression(logic_calc.IMPLY, A, B),
                            new logic_expression(logic_calc.IMPLY, B, C)),
                    new logic_expression(logic_calc.IMPLY, A, C)));
        });
        System.out.println("(d)");
        roll.dfs( () -> {
            System.out.println(new logic_expression(logic_calc.IMPLY,
                    new logic_expression(logic_calc.AND,
                            new logic_expression(logic_calc.IMPLY, A, B),
                            new logic_expression(logic_calc.IMPLY, A,
                                    new logic_expression(logic_calc.NOT, B))),
                    new logic_expression(logic_calc.NOT, A)));
        });
        System.out.println("(e)");
        roll.dfs( () -> {
            System.out.println(new logic_expression(logic_calc.IMPLY,
                    new logic_expression(logic_calc.IMPLY, A, B),
                    new logic_expression(logic_calc.NOT,
                            new logic_expression(logic_calc.IMPLY, B, A))));
        });
        System.out.println("(f)");
        atomicLetter.add(C);
        roll.dfs( () -> {
            System.out.println(new logic_expression(logic_calc.IMPLY,
                    new logic_expression(logic_calc.AND,
                            new logic_expression(logic_calc.OR, A, B),
                            new logic_expression(logic_calc.OR, B, C)),
                    new logic_expression(logic_calc.OR, A, C)));
        });
        System.out.println("(g)");
        atomicLetter.remove(C);
        roll.dfs( () -> {
            System.out.println(new logic_expression(logic_calc.IMPLY,
                    new logic_expression(logic_calc.AND, A, new logic_expression(logic_calc.NOT, A)),
                    B));
        });
        atomicLetter.add(C);
        System.out.println("(h)");
        roll.dfs( () -> {
            System.out.println(new logic_expression(logic_calc.IMPLY, C,
                    new logic_expression(logic_calc.EQUAL, A,
                            new logic_expression(logic_calc.OR, A,
                                    new logic_expression(logic_calc.AND, A, B)))));
        });
    }
}
