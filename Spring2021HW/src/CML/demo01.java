package CML;

import java.util.ArrayList;
import java.util.List;

// prove self-implication.

public class demo01 {

    private static final logic_calc[] calcs = {logic_calc.AND, logic_calc.OR, logic_calc.IMPLY, logic_calc.EQUAL};
    private static final logic_calc NOT = logic_calc.NOT;

    private static List<logic_expression> list = new ArrayList<>();

    public static void main(String[] args) {
        logic_expression p = new logic_expression("p");
        list.add(p);
        logic_expression.roll roll = new logic_expression.roll();
        roll.setExpressions(list);
        logic_function f = logic_function.createLogicFunction(2);
        roll.dfs( ()-> {
            list.forEach(System.out::print);
            System.out.println(f.getValue(logic_calc.IMPLY.value, p, p));
        });
    }

}
