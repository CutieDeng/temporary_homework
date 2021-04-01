package CML;

import java.util.ArrayList;
import java.util.List;

public class demo02 {

    private static final logic_calc[] attemptWays = {logic_calc.AND, logic_calc.OR, logic_calc.IMPLY, logic_calc.EQUAL};
    private static final logic_calc NOT = logic_calc.NOT;

    private static List<logic_expression> list = new ArrayList<>();

    public static void main(String[] args) {
        logic_expression p = new logic_expression("p1");
        logic_expression q = new logic_expression("p2");
        logic_expression r = new logic_expression("r");
        list.add(p);
        list.add(q);
//        list.add(r);
        logic_expression.roll roll = new logic_expression.roll();
        roll.setExpressions(list);
        logic_function f = logic_function.createLogicFunction(2);
        roll.dfs( ()-> {
            System.out.println(new logic_expression(logic_calc.OR, p, q));
            System.out.println(new logic_expression(logic_calc.NOT,
                    new logic_expression(logic_calc.AND,
                            new logic_expression(logic_calc.NOT, p),
                            new logic_expression(logic_calc.NOT, q))));
        });
    }

}
