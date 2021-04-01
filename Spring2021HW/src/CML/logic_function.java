package CML;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class logic_function {

    /**
     * n表示的是该逻辑函数的参量个数。
     */
    private int n;

    private static logic_function f2 = null;
    private static logic_function f1 = null;

    public static logic_function createLogicFunction(int n){
        switch (n){
            case 1:
                if (f1 == null){
                    f1 = new logic_function(1);
                }
                return f1;
            case 2:
                if (f2 == null){
                    f2 = new logic_function(2);
                }
                return f2;
            default:
                return new logic_function(n);
        }
    }

    private logic_function(int n){
        this.n = n;
    }

    /**
     * @param k k应该从1开始计数，表示第几个函数
     * @param f f应该从0开始计数，表示第几个状态！
     * @return
     */
    public boolean getValue(long k, long f){
        k = k - 1;
        f = get2N(n) - f - 1;
        return (k & (1L << f)) == 0;
    }

    /**
     * 该方法会检查若干个变量是否满足函数本身预设要求
     * @param boArr
     * @return
     */
    public boolean getValue(long k, boolean ... boArr){
        if (boArr.length != n){
            throw new RuntimeException();
        }
        return getValue(k, getSituation(boArr));
    }

    /**
     * 该方法用来求解若干个真值函数形成的状态函数值，即压缩状态！
     * @param boArr
     * @return
     */
    private static long getSituation(boolean ... boArr){
        long ans = 0;
        for (int i = 0; i < boArr.length; i++){
            if (!boArr[i]){
                ans += 1L << (boArr.length - i - 1);
            }
        }
        return ans;
    }

    private static long getSituation(Boolean ... boArr){
        long ans = 0;
        for (int i = 0; i < boArr.length; i++){
            if (!boArr[i]){
                ans += 1L << (boArr.length - i - 1);
            }
        }
        return ans;
    }

    public boolean getValue(long k, Boolean ... boArr){
        if (boArr.length != n){
            throw new RuntimeException();
        }
        return getValue(k, getSituation(boArr));
    }

    public static long get2N(long n){
        long ans = 1;
        long now = 2;
        while (n > 0){
            if ((n & 1) != 0){
                ans *= now;
            }
            now *= now;
            n >>>= 1;
        }
        return ans;
    }

    public static String getString(long n, long f){
        StringBuilder builder = new StringBuilder();
        while (--n >= 0){
            if ((f & (1<<n)) > 0){
                builder.append("false");
            }
            else{
                builder.append("true");
            }
            if (n > 0){
                builder.append(" ");
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        int n = new Scanner(System.in).nextInt();
        logic_function f = new logic_function(n);
        System.out.printf("     ");
        for (int k = 0; k < get2N(n); k++){
            System.out.printf(String.format("%%%ds", 6 * n ), getString(n, k));
        }
        System.out.println();
        for (int i = 1; i <= get2N(get2N(n)); i++){
            System.out.printf("%3d :", i);
            for (int k = 0; k < get2N(n); k++){
                System.out.printf(String.format("%%%ds", 6 * n ), f.getValue(i, k));
            }
            System.out.println();
        }
    }

    public boolean getValue(int logic_funcN, logic_expression... expression) {
        Boolean[] booleans = Arrays.stream(expression).map(e -> e.getValue()).collect(Collectors.toList()).toArray(new Boolean[0]);
        return getValue(logic_funcN, booleans);
    }
}
