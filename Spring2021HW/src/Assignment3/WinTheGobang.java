package Assignment3;

import java.util.ArrayList;
import java.util.Scanner;

public class WinTheGobang {

    private static byte[][] p;
    private static int row;
    private static int column;

    private static class point implements Comparable<point>{
        private int row;
        private int column;
        public point(int row, int column){
            this.row = row;
            this.column = column;
        }

        @Override
        public int compareTo(point o) {
            if (this.row == o.row){
                return this.column - o.column;
            }
            return this.row - o.row;
        }

        @Override
        public String toString(){
            return String.format("(%d,%d)", column + 1, row + 1);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        row = input.nextInt();
        column = row;
        char temChar;
        p = new byte[row][column];
        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j ++){
                temChar = input.next().charAt(0);
                switch (temChar){
                    case 'N':
                        p[i][j] = 0;
                        break;
                    case 'X':
                        p[i][j] = -1;
                        break;
                    case 'O':
                        p[i][j] = 1;
                        break;
                }
            }
        }
        ArrayList<point> ansList = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int i1 = 0; i1 < column; i1++) {
                if (isWin(i, i1)){
                    ansList.add(new point(i, i1));
                }
            }
        }
        ansList.forEach(System.out::println);
    }

    private static int[][] move = {
            {1, 0}, {1, 1}, {0, 1}, {-1, 1}
    };

    private static boolean isWin(int row, int column){
        if (p[row][column] != 0){
            return false;
        }
        p[row][column] = 1;
        int len;
        boolean ans = false;
        for (int i = 0; i < 4; i++) {
            len = getLength(row, column ,move[i][0], move[i][1]) + getLength(row, column, -move[i][0], -move[i][1]);
            if (len >= 4){
                ans = true;
                break;
            }
        }
        p[row][column] = 0;
        return ans;
    }

    private static int getLength(int row, int column, int rowMove, int columnMove){
        int ans = 0;
        while (
                (row + rowMove < WinTheGobang.row) &&
                        (row + rowMove >= 0) &&
                        (column + columnMove < WinTheGobang.column) &&
                        (column + columnMove >= 0) &&
                        ( p[row + rowMove][column + columnMove] == p[row][column])
        ){
            row += rowMove;
            column += columnMove;
            ans ++;
        }
        return ans;
    }

}
