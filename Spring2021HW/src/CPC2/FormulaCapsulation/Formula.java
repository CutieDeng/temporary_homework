package CPC2.FormulaCapsulation;

abstract public class Formula {
    private static int cnt = 0;

    protected final int id = cnt ++ ;


    public static void main(String[] args){
        System.out.println("test formula!");
    }
    protected FormulaKind kind;
    protected Formula(){
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Formula)){
            return false;
        }
        return this.id == ((Formula) o).id;
    }
}
