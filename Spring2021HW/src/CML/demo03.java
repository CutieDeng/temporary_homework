package CML;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class demo03 {

    private static logic_expression.roll roll = new logic_expression.roll();
    private static List<logic_expression> list = new ArrayList<>();

    public static void main(String[] args) {

        logic_expression p = new logic_expression("p");
        logic_expression q = new logic_expression("q");
        logic_expression r = new logic_expression("r");

        list.add(p);
        list.add(q);
        list.add(r);

        roll.setExpressions(list);
        if (false) {
            roll.dfs(() -> {
                System.out.println(new logic_expression(logic_calc.IMPLY,
                        new logic_expression(logic_calc.IMPLY,
                                new logic_expression(logic_calc.IMPLY, p, q),
                                new logic_expression(logic_calc.IMPLY, q, r)
                        ),
                        new logic_expression(logic_calc.IMPLY, q, r)));
            });
        }

        if (false) {
            roll.dfs( () -> {
                System.out.println(new logic_expression(logic_calc.IMPLY,
                        new logic_expression(logic_calc.IMPLY,
                                p,
                                new logic_expression(logic_calc.IMPLY, q, r)),
                        new logic_expression(logic_calc.IMPLY,
                                q,
                                new logic_expression(logic_calc.IMPLY, p, r))));
            });
        }

        if (true){
            roll.dfs( () -> {
                System.out.println(new logic_expression(logic_calc.IMPLY,
                        new logic_expression(logic_calc.IMPLY,
                                new logic_expression(logic_calc.IMPLY,
                                        p, q),
                                r),
                        new logic_expression(logic_calc.IMPLY,
                                new logic_expression(logic_calc.IMPLY, q, p),
                                r)));
            });
        }

        if (false){
//            证明了 -> 的运算顺序不能改变！
            roll.dfs( () -> {
                System.out.printf("%s ||| %s\n", new logic_expression(logic_calc.IMPLY, p, new logic_expression(logic_calc.IMPLY, q, r)),
                        new logic_expression(logic_calc.IMPLY,
                                new logic_expression(logic_calc.IMPLY, p, q),
                                r));
            });
        }

    }
}
