package StreamTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class demo_stream {

    public static void main(String[] args) {
        ArrayList<String> nameList = new ArrayList<>();
        nameList.add("Cutie");
        nameList.add("YYZ");
        nameList.add("Mathematical Logic");
        nameList.add("abc");
        nameList.add("zxy");
        for (int i = 0; i < 20; i++) {
            System.out.println(nameList.stream().collect(Collectors.toSet()));
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
