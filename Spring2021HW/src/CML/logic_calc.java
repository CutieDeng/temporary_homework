package CML;

public enum logic_calc {

    OR(2),
    AND(8),
    NOT(3),
    IMPLY(5),
    EQUAL(7);

    public final int value;
    logic_calc(int value){
        this.value = value;
    }
}
