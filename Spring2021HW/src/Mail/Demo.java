package Mail;

import java.lang.String;
import java.lang.reflect.Field;
import java.util.Arrays;

public class Demo {

    public static void main(String[] args){
        String test = new String(new char[]{'a', 'b', 'c'});
        String test2 = "abcd";
        char[] demo;
        try {
            Field value = String.class.getDeclaredField("value");
            value.setAccessible(true);
            Object o = value.get(test);
            System.out.println(Arrays.toString((char[]) o));
            o = value.get(test2);
            System.out.println(Arrays.toString((char[]) o));
            demo = (char[]) value.get(test2);
            char[] demo2 = new char[10];
//            System.arraycopy(demo, 0, demo2, 0, Math.min(10, demo.length));

            System.out.println(demo2);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }
}
