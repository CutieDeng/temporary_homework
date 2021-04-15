package CPC5.Capsulation;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("all")
final public class Formula{

    private final static Map<String, Formula> formulas = new HashMap<>();
    private final static List<Formula> lemmas = new ArrayList<>();
    private static boolean update = false;

    private final Formula left;
    private final Formula right;

    private final String name;
    private final int sonNum;

    private boolean correct = false;

    private final ArrayList<ArrayList<Formula>> requires = new ArrayList<>();
    private final ArrayList<Formula> leadSucceed = new ArrayList<>();

    private Formula(Formula left, Formula right) {
        this.left = left;
        this.right = right;
        this.name = String.format("( %s -> %s )", this.left, this.right);
        sonNum = 2;
        formulas.put(this.name, this);
    }

    private Formula(String name){
        this.left = null;
        this.right = null;
        this.name = name;
        sonNum = 0;
        formulas.put(this.name, this);
    }

    private Formula(Formula right){
        this.left = null;
        this.right = right;
        this.name = String.format("( NOT %s )", this.right);
        sonNum = 1;
        formulas.put(this.name, this);
    }

    public static Formula newInstance(String name){
        if (name.contains("(") && name.contains(")")){
            throw new RuntimeException();
        }
        if (formulas.containsKey(name)){
            return formulas.get(name);
        }
        return new Formula(name);
    }

    public static Formula newInstance(Formula not){
        String tem = String.format("(NOT %S)", not);
        if (formulas.containsKey(tem)){
            return formulas.get(tem);
        }
        return new Formula(not);
    }

    public static Formula newInstance(Formula left, Formula right){
        String tem = String.format("(%s -> %s)", left, right);
        if (formulas.containsKey(tem)){
            return formulas.get(tem);
        }
        return new Formula(left, right);
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Formula)){
            return false;
        }
        return this.name.equals(((Formula) o).name);
    }

    @Override
    public String toString(){
        return this.name;
    }

    public static final Formula theorem1 = newInstance(
            newInstance("a"),
            newInstance(
                    newInstance("b"),
                    newInstance("a")
            )
    );

    public static final Formula theorem2 = newInstance(
            newInstance(
                    newInstance(
                            newInstance("a")
                    ),
                    newInstance(
                            newInstance("b")
                    )
            ),
            newInstance(
                    newInstance("b"),
                    newInstance("a")
            )
    );

    public static final Formula theorem3 = newInstance(
            newInstance(
                    newInstance("a"),
                    newInstance(
                            newInstance("b"),
                            newInstance("c")
                    )
            ),
            newInstance(
                    newInstance(
                            newInstance("a"),
                            newInstance("b")
                    ),
                    newInstance(
                            newInstance("a"),
                            newInstance("c")
                    )
            )
    );

    static {
        theorem1.correct = true;
        theorem2.correct = true;
        theorem3.correct = true;
    }

    public Tuple<Boolean, Map<Formula, Formula>> match(Formula theorem){
//        if (!theorem.correct){
//            return new Tuple<>(false, null);
//        }
        Map<Formula, Formula> temporary = new HashMap<>();
        boolean ans = completeMatch(theorem, temporary);
        return new Tuple<>(Boolean.valueOf(ans), temporary);
    }

    public boolean completeMatch(Formula theorem){
        return completeMatch(theorem, new HashMap<>());
    }

    public boolean completeMatch(Formula theorem, Map<Formula, Formula> temporary){
        if (this.sonNum == theorem.sonNum){
            if (this.left != null){
                return this.left.completeMatch(theorem.left, temporary) && this.right.completeMatch(theorem.right, temporary);
            }
            if (this.right != null){
                return this.right.completeMatch(theorem.right, temporary);
            }
            if (temporary.containsKey(theorem)){
                if (!temporary.get(theorem).equals(this)){
                    return false;
                }
            }
            else{
                temporary.put(theorem, this);
            }
            return true;
        }
        if (theorem.sonNum == 0){
            if (temporary.containsKey(theorem)){
                if (!temporary.get(theorem).equals(this)){
                    return false;
                }
            }
            else{
                temporary.put(theorem, this);
            }
            return true;
        }
        return false;
    }

    public static Formula valueOf(String name){
        Stack<Formula> formulaStack = new Stack<>();
        String elementName;
        boolean not = false;
        int imply = 0;
        firstLoop:
        for (int index = 0; index < name.length();) {
            while (name.charAt(index) == '(' || name.charAt(index) == ' '){
                index++;
                if (index >= name.length()){
                    break firstLoop;
                }
            }
            switch (name.charAt(index)){
                case '>':
                    index--;
                case '-':
                    index += 2;
                    imply ++;
                    break;
                case ')':
                    index ++;
                    if (imply > 0){
                        Formula tem = formulaStack.pop();
                        formulaStack.push(newInstance(formulaStack.pop(), tem));
                        imply--;
                    }
                    break;
                case 'N':
                    not = true;
                    index++;
                    if (name.charAt(index) == 'O' || name.charAt(index) == 'o'){
                        index++;
                        if (name.charAt(index) == 'T' || name.charAt(index) == 't'){
                            index++;
                        }
                    }
                    break;
                default:
                    int endP = name.indexOf(" ", index);
                    if (endP == -1 || (name.indexOf("-", index) < endP && name.indexOf("-", index) != -1)){
                        endP = name.indexOf("-", index);
                    }
                    if (endP == -1 || (name.indexOf(")", index) < endP && name.indexOf(")", index) != -1)){
                        endP = name.indexOf(")", index);
                    }
                    if (endP == -1 || (name.indexOf(">", index) < endP && name.indexOf(">", index) != -1)){
                        endP = name.indexOf(">", index);
                    }
                    if (endP == -1){
                        endP = name.length();
                    }
                    if (not){
                        not = false;
                        formulaStack.push(newInstance(newInstance(
                                name.substring(index, endP)
                        )));
                    }
                    else{
                        formulaStack.push(newInstance(
                                name.substring(index, endP)
                        ));
                    }
                    index = endP;
            }
        }
        while (imply > 0){
            Formula tem = formulaStack.pop();
            formulaStack.push(newInstance(formulaStack.pop(), tem));
            imply--;
        }
        if (formulaStack.size() > 1){
            System.err.println("Wrong stack size!");
        }
        return formulaStack.pop();
    }

    @Deprecated
    private static Formula valueOf(int index, String name){
        Formula i;
        if (name.charAt(index) == '('){
            i = valueOf(index + 1, name);
        }
        else{
            if (name.substring(index).startsWith("NOT")){
                return newInstance(valueOf(index + 4, name));
            }
            int lastIndex = name.indexOf(" ", index);
            if (lastIndex < index){
                throw new RuntimeException();
            }
            i = newInstance(name.substring(index, lastIndex + 1));
        }
        throw new RuntimeException("I don't know.");
    }

    public void attemptFit(Formula theorem){

    }

    public List<Formula> proveFrom(Formula theorem) {
        Tuple<Boolean, Map<Formula, Formula>> tuple = match(theorem);
        if (tuple.value1){
            this.correct = true;
            lemmas.add(this);
            update = true;
            return null;
        }
        List<Formula> needProve = new ArrayList<>();
        if (theorem.left != null){
            needProve.add(theorem.left);
        }
        Map<Formula, Formula> map = proveFrom(theorem.right, needProve);
        List<Formula> proves = needProve.stream().map(formula -> {
            return formula.clone(map);
        }).collect(Collectors.toList());
        return proves;
    }

    public Map<Formula, Formula> proveFrom(Formula theorem, List<Formula> proves){
        Tuple<Boolean, Map<Formula, Formula>> tuple = match(theorem);
        if (tuple.value1){
            this.correct = true;
            lemmas.add(this);
            update = true;
            return tuple.value2;
        }
        if (theorem.sonNum == 2){
            proves.add(theorem.left);
            return proveFrom(theorem.right, proves);
        }
        if (theorem.sonNum == 1){
            if (this.sonNum == 1){
                return this.right.proveFrom(theorem.right, proves);
            }
            else{
                return null;
            }
        }
//        throw new RuntimeException("Unexpected situation.");
        System.err.println("Unexpected situation sonNum = 0.");
        return null;
    }

    public Formula clone(Map<Formula, Formula> formulaMap){
        if (formulaMap.containsKey(this)){
            return formulaMap.get(this);
        }
        switch (this.sonNum){
            case 0:
                return this;
            case 1:
                return newInstance(this.right.clone(formulaMap));
            case 2:
                return newInstance(this.left.clone(formulaMap), this.right.clone(formulaMap));
            default:
                throw new RuntimeException("Unexpected situation [clone]");
        }
    }


}
