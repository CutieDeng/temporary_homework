package Assignment4;

import java.util.Arrays;
import java.util.stream.Collectors;

@SuppressWarnings("all")
public class MagicStrings {
    private int[] priority;
    private String[] ss;

    private static final int[] PRIORITY_DEFAULT;
    static {
        PRIORITY_DEFAULT = new int[26];
        for (int i = 0; i < PRIORITY_DEFAULT.length; i++) {
            PRIORITY_DEFAULT[i] = i;
        }
    }

    public MagicStrings(String s){
        setPriority(PRIORITY_DEFAULT);
        setSs(s);
    }

    public MagicStrings(int[] priority, String s){
        setPriority(priority);
        setSs(s);
    }

    public MagicStrings(String priority, String s){
        setPriority(priority);
        setSs(s);
    }

    public void setPriority(int[] priority){
        this.priority = priority.clone();
    }

    public void setPriority(String priority){
        Integer[] integers = Arrays.stream(priority.split("\\s+")).map(Integer::parseInt).collect(Collectors.toList()).toArray(new Integer[0]);
        if (this.priority == null){
            this.priority = new int[integers.length];
        }
        for (int i = 0; i < integers.length; i++) {
            this.priority[i] = integers[i];
        }
    }

    public void setPriority(char c, int priority){
        if (c >= 'a'){
            this.priority[c-'a'] = priority;
            return ;
        }
        this.priority[c-'A'] = priority;
    }

    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        builder.append("abc");
        System.out.println(builder);
    }

    public void setSs(String input){
        String[] split = input.split("\\s+");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            for (int i1 = 0; i1 < split[i].length(); i1++) {
                if (!checkCharacter(split[i].charAt(i1))){
                    continue;
                }
                builder.append(split[i].charAt(i1));
            }
            split[i] = builder.toString();
            builder = new StringBuilder();
        }
        this.ss = split;
    }

    private static boolean checkCharacter(char c){
        return Character.isAlphabetic(c);
    }

    public int stringNum(){
        if (ss == null){
            return 0;
        }
        return ss.length;
    }

    public int compareString(String a, String b){
        int minimum = Math.min(a.length(), b.length());
        a = a.toLowerCase();
        b = b.toLowerCase();
        for (int i = 0; i < minimum; i++){
            int ans = priority[a.charAt(i) - 'a'] - priority[b.charAt(i) - 'a'];

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

    public void sortSs(){
        if (ss == null){
            return ;
        }

        Arrays.sort(ss, (a, b) -> {
//            return compareString(a, b) * -1;
            return compareString(b, a);
        });
    }

    public String getStrings(){
        if (ss==null){
            return null;
        }
        StringBuilder builder = new StringBuilder();
        for (String s : ss) {
            builder.append(s).append(" ");
        }
        String ans = builder.toString();
        if (ans.length() > 0){
            ans = ans.substring(0, ans.length() - 1);
        }
        return ans;
    }
}
