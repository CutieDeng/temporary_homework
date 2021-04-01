package Assignment2;

import java.util.Scanner;

public class CS301 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s1 = input.next();
        String s2 = input.next();
        StringBuilder ans = new StringBuilder();
        int nowNum = 0;
        int pointS1 = s1.length()-1;
        int pointS2 = s2.length()-1;
        while (pointS1>=0 || pointS2 >= 0){
            nowNum += pointS1 >= 0 ? (s1.charAt(pointS1--) - '0') : 0;
            nowNum += pointS2 >= 0 ? (s2.charAt(pointS2--) - '0') : 0;
            ans.insert(0, nowNum%2);
            nowNum /= 2;
        }
        while (nowNum > 0){
            ans.insert(0, nowNum%2);
            nowNum /= 2;
        }
        while (ans.toString().charAt(0) == '0'){
            ans.delete(0, 1);
        }
        System.out.println(ans);

    }

}
