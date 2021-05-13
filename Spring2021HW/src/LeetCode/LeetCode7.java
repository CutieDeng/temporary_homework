package LeetCode;

import java.util.Scanner;

public class LeetCode7 {

    @SuppressWarnings("unused")
    public int reverse(int x){
        String number = String.format("%d", x);
        StringBuilder builder;
        boolean negative = false;
        if (number.startsWith("-")){
            negative = true;
            number = number.substring(1);
        }
        builder = new StringBuilder(number).reverse();
        try {
            return Integer.parseInt(builder.toString()) * (negative ? -1 : 1);
        }
        catch (NumberFormatException exception){
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(new LeetCode7().reverse(scanner.nextInt()));
    }

}
