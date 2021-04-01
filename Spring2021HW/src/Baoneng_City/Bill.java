package Baoneng_City;

import java.util.Scanner;

public class Bill {

    private static enum Level{
        Diamond(0),
        Gold(1),
        Silver(2),
        Ordinary(3);
        private int value;
        private double cutOff;
        private Level(int value){
            this.value = value;
        }
        static {
            Diamond.cutOff = 0.7;
            Gold.cutOff = 0.8;
            Silver.cutOff = 0.9;
            Ordinary.cutOff = 1;
        }
        private double getCutOff(){
            return cutOff;
        }
    }

    public static void main(String[] args) {
        double cost = 0;
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        Level nowLevel = Level.valueOf(input.next());
        double piece;
        int number;
        for (int i = 0; i < n; i++){
            piece = input.nextDouble();
            number = input.nextInt();
            cost += piece * number;
        }
        cost *= nowLevel.cutOff;
        System.out.println(String.format("%.1f", cost));
    }
}
