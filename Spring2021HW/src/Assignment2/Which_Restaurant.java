package Assignment2;

import java.util.Scanner;

public class Which_Restaurant {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] r = new int[n];
//        目前正在分析的不下降子序列的右端数字的值
        int nowNum = -1;
//        目前正在分析的不下降子序列的长度
        int len = 0;
//        目前记录的最长不下降子序列的长度
        int lenMax = 0;
//        目前记录的最长不下降子序列的右端点在seq中的位置
        int pointMax = -1;
        for (int i = 0; i < n; i++){
            r[i] = input.nextInt();
            if (r[i] >= nowNum){
                len ++;
                if (len >= lenMax){
                    pointMax = i;
                    lenMax = len;
                }
            }
            else {
                len = 1;
            }
            nowNum = r[i];
        }
        for (int k = pointMax - lenMax + 1; k <= pointMax; k++){
            System.out.print(r[k] + " ");
        }
    }

}
