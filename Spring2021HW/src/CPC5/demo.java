package CPC5;

import CPC5.Capsulation.Formula;

import java.util.List;
import java.util.Scanner;

import static CPC5.Capsulation.Formula.*;

@SuppressWarnings("all")
public class demo {

    public static void prove(Formula formula){
        List<Formula> pro = formula.proveFrom(theorem1);
        if (pro == null){
            System.out.println(pro + " satisfies THEOREM 1");
        }
        else{
            System.out.println(formula + " need these formulas:");
            pro.forEach(System.out::println);
            for (Formula formula1 : pro) {
                prove(formula1);
            }
        }
    }

    public static void main(String[] args) {
        Formula a = newInstance("A");
        Formula b = newInstance(a,  a);
        Formula c = newInstance("B");
        Formula d = newInstance(c, c);
        Formula x = newInstance(
                newInstance(
                        newInstance("t1"),
                        newInstance(
                                newInstance("t1"),
                                newInstance("t1")
                        )
                ),
                newInstance(
                        newInstance(
                                newInstance("t1"),
                                newInstance("t1")
                        ),
                        newInstance(
                                newInstance("t1"),
                                newInstance("t1")
                        )
                )
        );
        Formula test = newInstance(
                newInstance(
                        newInstance(
                                newInstance(
                                        newInstance("A"),
                                        newInstance("B")
                                )
                        ),
                        newInstance(
                                newInstance(
                                        newInstance("B"),
                                        newInstance("C")
                                )
                        )
                ),
                newInstance(
                        newInstance(
                                newInstance("B"),
                                newInstance("C")
                        ),
                        newInstance(
                                newInstance("A"),
                                newInstance("B")
                        )
                )
        );
        Formula f = newInstance(
                newInstance("A"),
                newInstance(
                        newInstance("B"),
                        newInstance("A")
                )
        );
        f = test;
        prove(f);
        System.exit(0);
//        System.out.println(theorem1);
//        System.out.println(theorem2);
//        System.out.println(theorem3);
        Scanner input = new Scanner(System.in);
        try {
            System.out.println(String.format("成功获取表达式：%s", f));
            System.out.println(String.format("是否满足公理1：%s", f.completeMatch(theorem1)));
            f.proveFrom(theorem1);
            System.out.println(String.format("是否满足公理2：%s", f.completeMatch(theorem2)));
            f.proveFrom(theorem2);
            System.out.println(String.format("是否满足公理3：%s", f.completeMatch(theorem3)));
            f.proveFrom(theorem3);
        }catch (RuntimeException r){
            System.err.println("获取表达式失败！");
        }
    }
}
