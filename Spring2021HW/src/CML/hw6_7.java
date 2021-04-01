package CML;

import java.util.ArrayList;
import java.util.List;

public class hw6_7 {

    private static List<logic_expression> atomicLetter = new ArrayList<>();

    private static logic_expression.roll roll = new logic_expression.roll();

    public static void main(String[] args) {

        logic_expression A = new logic_expression("A");
        logic_expression B = new logic_expression("B");
        logic_expression C = new logic_expression("C");
        logic_expression D = new logic_expression("D");
        logic_expression E = new logic_expression("E");

        atomicLetter.add(A);
        atomicLetter.add(B);
        atomicLetter.add(C);
        atomicLetter.add(D);
        atomicLetter.add(E);

        roll.setExpressions(atomicLetter);
        if (false)
            roll.dfs(() -> {
                System.out.println(new logic_expression(logic_calc.IMPLY,
                        new logic_expression(logic_calc.AND,
                                new logic_expression(logic_calc.AND,
                                        new logic_expression(logic_calc.IMPLY,
                                                new logic_expression(logic_calc.IMPLY,
                                                        A, B),
                                                new logic_expression(logic_calc.IMPLY,
                                                        B, A)),
                                        new logic_expression(logic_calc.IMPLY, C, D)),
                                C),
                        new logic_expression(logic_calc.OR,
                                new logic_expression(logic_calc.EQUAL, D, C),
                                new logic_expression(logic_calc.AND, E, new logic_expression(logic_calc.NOT, E)))));
            });
        roll.dfs(() -> {
            System.out.println(new logic_expression(logic_calc.IMPLY,
                    new logic_expression(logic_calc.AND,
                            new logic_expression(logic_calc.IMPLY,
                                    new logic_expression(logic_calc.IMPLY,
                                            A, B),
                                    new logic_expression(logic_calc.IMPLY,
                                            B, A)),
                            new logic_expression(logic_calc.IMPLY, C, D)),
                    new logic_expression(logic_calc.OR,
                            new logic_expression(logic_calc.EQUAL, D, C),
                            new logic_expression(logic_calc.AND, E, new logic_expression(logic_calc.NOT, E)))));
        });

    }
}
