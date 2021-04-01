package Assignment2;

import java.util.Scanner;

public class SUSTechRatio {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int n;
        int m;
        n = input.nextInt();
        m = input.nextInt();
        int gcd = getGreatestCommonDivisor(n, m, true);
        n /= gcd;
        m /= gcd;
        System.out.println(n + " " + m);
    }

    public static int getGreatestCommonDivisor(int a, int b, boolean bo){

        if (bo){
            if (a==0 || b==0){
                throw new RuntimeException("Find 0 in gcd!");
            }
        }

        return (b==0) ? a : getGreatestCommonDivisor(b, a%b, false);

    }

}
