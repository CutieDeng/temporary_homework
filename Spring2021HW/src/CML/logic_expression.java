package CML;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class logic_expression {

    public static class roll{
        private List<logic_expression> expressions;
        public void dfs(Runnable runnable){
            run(0, runnable);
        }

        @Deprecated
        public void setExpressions(logic_expression[] expressions) {
            this.expressions = Arrays.stream(expressions).filter(e -> e.isValue).collect(Collectors.toList());
        }

        public void setExpressions(List<logic_expression> expressions){
            this.expressions = expressions;
        }

        private void run(int depth, Runnable runnable){
            if (depth < expressions.size()){
                expressions.get(depth).value = true;
                run(depth + 1, runnable);
                expressions.get(depth).value = false;
                run(depth+1, runnable);
            }
            else{
                runnable.run();
            }
        }
    }

    private boolean value ;
    private boolean isValue = false;
    private logic_expression[] expression = null;
    private int logic_funcN = -1;
    private String name = null;

    public boolean getValue(){
        if (!isValue){
            value = logic_function.createLogicFunction(expression.length).getValue(logic_funcN, expression);
        }
        return value;
    }

    public logic_expression(String name, boolean value) {
        this.value = value;
        isValue = true;
        this.name = name;
    }

    public logic_expression(String name){
        this.isValue = true;
        this.name = name;
    }

    public logic_expression(logic_calc calc, logic_expression... logic_expressions){
        this(calc.value, logic_expressions);
    }

    public logic_expression(int logic_funcN, logic_expression... logic_expressions) {
        this.expression = logic_expressions;
        this.logic_funcN = logic_funcN;
        if (logic_expressions.length == 1){
            if (logic_funcN == logic_calc.NOT.value){
                this.name = String.format("(%3s %s)", "NOT", logic_expressions[0]);
            }
            else{
                this.name = String.format("([%d] %s)", logic_funcN, logic_expressions[0]);
            }
        }
        else if (logic_expressions.length == 2){
            switch (logic_funcN){
                case 5:
                    this.name = String.format("(%s %5s %s)", logic_expressions[0], "IMPLY", logic_expressions[1]);
                    break;
                case 2:
                    this.name = String.format("(%s  OR   %s)", logic_expressions[0], logic_expressions[1]);
                    break;
                case 8:
                    this.name = String.format("(%s  AND  %s)", logic_expressions[0], logic_expressions[1]);
                    break;
                case 7:
                    this.name = String.format("(%s %5s %s)", logic_expressions[0], "EQUAL", logic_expressions[1]);
                    break;
                default:
                    this.name = String.format("(%s [%3d] %s)", logic_expressions[0], logic_funcN, logic_expressions[1]);
            }
        }
        else{
            throw new RuntimeException("Too long of the logic expressions.");
        }
    }

    @Override
    public String toString() {
        return String.format("[%s %5s]", name, getValue());
    }
}
