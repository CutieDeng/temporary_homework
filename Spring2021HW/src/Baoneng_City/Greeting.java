package Baoneng_City;

import java.util.Scanner;

public class Greeting {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String name = input.next();
        String id = input.next();
        if (isTrue(id)){
            System.out.println(name + ", welcome to Baoneng City!");
        }else {
            System.out.println(id);
        }
    }
    private static boolean isTrue(String s){
        if (s.length() != 8)
            return false;
        try {
            int prefix = Integer.parseInt(s.substring(0, 3));
            if (!(prefix < 115 || prefix > 120)){
                return true;
            }
        }catch (RuntimeException e){
        }
        return false;
    }
}
