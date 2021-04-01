package Baoneng_City;

import java.util.Scanner;

public class VIP_System {

    private static enum Level{
        Diamond(0),
        Gold(1),
        Silver(2),
        Ordinary(3);
        private int value;
        private Level(int value){
            this.value = value;
        }
        private Level singleAmountJudge(int singleAmount){
            if (this.value > 0 && singleAmount >= 5000){
                return Diamond;
            }
            if (this.value > 1 && singleAmount >= 3000){
                return Gold;
            }
            if (this.value > 2 && singleAmount >= 1500){
                return Silver;
            }
            return this;
        }
        private Level judgeAmount(int times, int total){
            if (this.value > 0 && times >= 10 && total >= 3000){
                return Diamond;
            }
            if (this.value > 1 && times >= 5 && total >= 2000){
                return Gold;
            }
            if (this.value > 2 && times >= 3 && total >= 1000){
                return Silver;
            }
            return this;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        Level nowLevel = Level.Ordinary;
        int t = 0;
        int now ;
        for (int i = 0; i < T; i++){
            now = input.nextInt();
            nowLevel = nowLevel.singleAmountJudge(now);
            t += now;
        }
        nowLevel = nowLevel.judgeAmount(T, t);
        System.out.println(nowLevel);
    }
}
