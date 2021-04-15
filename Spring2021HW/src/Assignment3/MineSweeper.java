package Assignment3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MineSweeper {
    public static void main(String[] args) {
        int[][] ans;
        Reader input = new Reader(System.in);
        int n = input.nextInt();
        ans = new int[n][n];
        char[] temC;
        String line;
        for (int i = 0; i < n; i++) {
            line = input.next();
            temC = line.toCharArray();
            for (int i1 = 0; i1 < n; i1++) {
                if (temC[i1] == '*'){
                    ans[i][i1] += 9;
                    for (int row = Math.max(0, i-1); row < Math.min(n, i+2); row ++){
                        for (int column = Math.max(0, i1-1); column < Math.min(n, i1+2); column++){
                            ans[row][column] += 1;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (ans[i][j] > 8){
                    System.out.print("F");
                }
                else{
                    System.out.printf("%d", ans[i][j]);
                }
            }
            System.out.println();
        }
    }

    private static class Reader {
        BufferedReader in;
        StringTokenizer tokenizer;
        public Reader(InputStream inputStream) {
            in = new BufferedReader(new InputStreamReader(inputStream));
        }
        private String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(in.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }
        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
