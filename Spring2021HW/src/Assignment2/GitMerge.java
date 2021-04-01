package Assignment2;

import java.util.Scanner;

public class GitMerge {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int[] s1 = new int[n];
        for(int i = 0; i < n; i++){
           s1[i] = input.nextInt();
        }
        int point = 0;
        int nowR;
        for(int i = 0; i < m; i++){
            if (point == n){
                break;
            }
            nowR = input.nextInt();
            if (nowR == s1[point]){
                point++;
            }
        }
        System.out.println(point == n ? "Yes" : "No");
    }
}
