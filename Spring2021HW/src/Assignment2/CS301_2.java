package Assignment2;

import java.math.BigInteger;
import java.util.Scanner;

public class CS301_2 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println(
                input.nextBigInteger(2).add(input.nextBigInteger(2)).toString(2)
        );

    }

}
