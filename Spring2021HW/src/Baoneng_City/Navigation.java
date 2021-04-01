package Baoneng_City;

import java.util.Scanner;

public class Navigation {

    public static void main(String[] args) {
        int x, y;
        Scanner input = new Scanner(System.in);
        x = input.nextInt();
        y = input.nextInt();
        String r = input.next();
        while (!r.equals("E")) {
            char c = r.charAt(0);
            switch (c) {
                case 'a':
                    x--;
                    break;
                case 's':
                    y--;
                    break;
                case 'd':
                    x++;
                    break;
                case 'w':
                    y++;
                    break;
            }
            r = input.next();
        }
        System.out.println(String.format("%d %d",x,y));
    }
}
