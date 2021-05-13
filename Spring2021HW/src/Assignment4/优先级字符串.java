package Assignment4;

import java.util.Arrays;
import java.util.stream.Collectors;

@SuppressWarnings("all")
public class 优先级字符串 {
    private int[] 字符优先级;
    private String[] 无意义字符串;

    private static final int[] 字符优先级_缺省值;
    static {
        字符优先级_缺省值 = new int[26];
        for (int i = 0; i < 字符优先级_缺省值.length; i++) {
            字符优先级_缺省值[i] = i;
        }
    }

    public 优先级字符串(String 无意义字符串){
        设置字符串优先级(字符优先级_缺省值);
        设置无意义字符串(无意义字符串);
    }

    public 优先级字符串(int[] 字符优先级, String 无意义字符串){
        设置字符串优先级(字符优先级);
        设置无意义字符串(无意义字符串);
    }

    public 优先级字符串(String 字符优先级, String 无意义字符串){
        设置字符串优先级(字符优先级);
        设置无意义字符串(无意义字符串);
    }

    public void 设置字符串优先级(int[] 字符优先级){
        this.字符优先级 = 字符优先级.clone();
    }

    public void 设置字符串优先级(String 字符串优先级){
        Integer[] integers = Arrays.stream(字符串优先级.split("\\s+")).map(Integer::parseInt).collect(Collectors.toList()).toArray(new Integer[0]);
        if (this.字符优先级 == null){
            this.字符优先级 = new int[integers.length];
        }
        if (this.字符优先级.length < integers.length){
            System.err.println("字符优先级数组长度过小。");
            this.字符优先级 = new int[integers.length];
        }
        for (int i = 0; i < integers.length; i++) {
            this.字符优先级[i] = integers[i];
        }
    }

    public void 设置字符串优先级(char 字符, int 优先级){
        if (字符 >= 'a'){
            this.字符优先级[字符-'a'] = 优先级;
            return ;
        }
        this.字符优先级[字符-'A'] = 优先级;
    }

    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        builder.append("abc");
        System.out.println(builder);
    }

    public void 设置无意义字符串(String 无意义字符串){
        String[] split = 无意义字符串.split("\\s+");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            for (int i1 = 0; i1 < split[i].length(); i1++) {
                if (!是一个字母(split[i].charAt(i1))){
                    continue;
                }
                builder.append(split[i].charAt(i1));
            }
            split[i] = builder.toString();
            builder = new StringBuilder();
        }
        this.无意义字符串 = split;
    }

    private static boolean 是一个字母(char c){
        return Character.isAlphabetic(c);
    }

    public int 无意义字符串长度(){
        if (无意义字符串 == null){
            return 0;
        }
        return 无意义字符串.length;
    }

    public int 比较字符串(String a, String b){
        int minimum = Math.min(a.length(), b.length());
        a = a.toLowerCase();
        b = b.toLowerCase();
        for (int i = 0; i < minimum; i++){
            int ans = 字符优先级[a.charAt(i) - 'a'] - 字符优先级[b.charAt(i) - 'a'];

            if (ans > 0){
                return 1;
            }
            if (ans < 0){
                return -1;
            }
        }
        if (a.length() < b.length()){
            return -1;
        }
        if (a.length() > b.length()){
            return 1;
        }
        return 0;
    }

    public void 排序无意义字符串(){
        if (无意义字符串 == null){
            return ;
        }

        Arrays.sort(无意义字符串, (a, b) -> {
//            return compareString(a, b) * -1;
            return 比较字符串(b, a);
        });
    }

    public String 获取无意义字符串(){
        if (无意义字符串 ==null){
            return null;
        }
        StringBuilder builder = new StringBuilder();
        for (String s : 无意义字符串) {
            builder.append(s).append(" ");
        }
        String ans = builder.toString();
        if (ans.length() > 0){
            ans = ans.substring(0, ans.length() - 1);
        }
        return ans;
    }
}
