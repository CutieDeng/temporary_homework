package CPC5.Capsulation;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
public class Tuple2 {
    private final List list;

    public Tuple2(Object... args) {
        list = Arrays.asList(args);
    }

    public Object get(int i){
        return list.get(i);
    }

    public <T> T get(int i, T args){
        return (T) list.get(i);
    }
}