package CML;

import java.util.ArrayList;
import java.util.List;

public class test01 {

    private static List<logic_expression> atomicLetter = new ArrayList<>();

    private static logic_expression.roll roll = new logic_expression.roll();

    public static void main(String[] args) {
        logic_expression A = new logic_expression("A");
        logic_expression B = new logic_expression("B");
        atomicLetter.add(A);
        atomicLetter.add(B);

        roll.setExpressions(atomicLetter);

        System.out.println("!");
        roll.dfs( () -> {
            logic_expression e_1 = new logic_expression(logic_calc.AND,
                    new logic_expression(logic_calc.IMPLY, A, B),
                    new logic_expression(logic_calc.IMPLY, B, A));
            logic_expression e_2 = new logic_expression(logic_calc.EQUAL, A, B);
            System.out.printf("%s ||| %s\n", e_1, e_2);
        });

        System.exit(0);

        System.out.println("(1,1)");
        roll.dfs( () -> {
            logic_expression e1 = new logic_expression(logic_calc.EQUAL, A, B);
            logic_expression e2 = new logic_expression(logic_calc.AND,
                    new logic_expression(logic_calc.IMPLY, A, B),
                    new logic_expression(logic_calc.IMPLY, B, A));
            System.out.printf("%s ||| %s\n", e1, e2);
        });

        System.out.println("(1,2)");
        roll.dfs( () -> {
            logic_expression e = new logic_expression(logic_calc.OR,
                    new logic_expression(logic_calc.NOT,
                            new logic_expression(logic_calc.IMPLY, A, B)),
                    new logic_expression(logic_calc.NOT,
                            new logic_expression(logic_calc.IMPLY, B, A)));
            System.out.println(e);
        });

        System.out.println("(2,1)");
        roll.dfs( () -> {
            logic_expression e1 = new logic_expression(logic_calc.IMPLY, A, B);
            logic_expression e2 = new logic_expression(logic_calc.OR, new logic_expression(logic_calc.NOT, A), B);
            System.out.printf("%s ||| %s\n", e1, e2);
        });
        System.out.println("(2,2)");
        roll.dfs( () -> {
            logic_expression e1 = new logic_expression(logic_calc.IMPLY, A, B);
            logic_expression e2 = new logic_expression(logic_calc.NOT,
                    new logic_expression(logic_calc.AND, A,
                            new logic_expression(logic_calc.NOT, B)));
            System.out.printf("%s ||| %s\n", e1, e2);
        });
        System.out.println("(3,1)");
        roll.dfs( () -> {
            logic_expression e1 = new logic_expression(logic_calc.OR, A, B);
            logic_expression e2 = new logic_expression(logic_calc.NOT,
                    new logic_expression(logic_calc.AND,
                            new logic_expression(logic_calc.NOT, A),
                            new logic_expression(logic_calc.NOT, B)));
            System.out.printf("%s ||| %s\n", e1, e2);
        });
        System.out.println("(3,2)");
        roll.dfs( ()->{
            logic_expression e1 = new logic_expression(logic_calc.AND, A, B);
            logic_expression e2 = new logic_expression(logic_calc.NOT,
                    new logic_expression(logic_calc.OR,
                            new logic_expression(logic_calc.NOT, A),
                            new logic_expression(logic_calc.NOT, B)));
            System.out.printf("%s ||| %s\n", e1, e2);
        });
        System.out.println("(4,1)");
        roll.dfs( () -> {
            logic_expression e1 = new logic_expression(logic_calc.OR, A, B);
            logic_expression e2 = new logic_expression(logic_calc.IMPLY,
                    new logic_expression(logic_calc.NOT, A),
                    B);
            System.out.printf("%s ||| %s\n", e1, e2);
        });
        System.out.println("(4,2)");
        roll.dfs( () -> {
            logic_expression e1 = new logic_expression(logic_calc.AND, A, B);
            logic_expression e2 = new logic_expression(logic_calc.NOT,
                    new logic_expression(logic_calc.IMPLY,
                            A,
                            new logic_expression(logic_calc.NOT, B)));
            System.out.printf("%s ||| %s\n", e1, e2);
        });
    }
}
