package MailAgreement;

import org.junit.jupiter.api.Test;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;


@SuppressWarnings("all")
public class Demo {

    @Test
    public void test1() {
        Properties properties = System.getProperties();
        properties.list(System.out);
        Hashtable hashtable = properties;
        hashtable.clear();
    }

    @Test
    public void test2(){
        Enumeration<String> enumeration = new Enumeration<String>() {
            @Override
            public boolean hasMoreElements() {
                return false;
            }

            @Override
            public String nextElement() {
                return null;
            }
        };
        System.out.println(enumeration);
    }

}
