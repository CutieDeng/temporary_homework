package CPC;

public class Decorator {
    private static final String NAME_DEFAULT = "unnamed";

    private String name;

    private boolean allowSet = false;

    private Formula formula = null;

    public Decorator(String name, boolean allowSet) {
        this.name = name;
        this.allowSet = allowSet;
        if (!allowSet){
            formula = new AtomicFormula();
        }
    }



    public Formula getFormula() {
        return formula;
    }

    public boolean setFormula(Formula formula) {
        if (allowSet){
            this.formula = formula;
            allowSet = false;
            return true;
        }
        else{
            return this.formula.equals(formula);
        }
    }
}
