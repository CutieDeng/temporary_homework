package CPC4;

import java.util.ArrayList;

public class Demo {

    private static ArrayList<Integer> tem = new ArrayList<>();

    public static void main(String[] args) {
        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node(A, B);
        System.out.println(C);
    }

    public static void needProve(ArrayList<Node> nodes){
        System.out.println(String.format("\nNow try to prove these formulas:"));
        nodes.forEach(System.out::println);
        System.out.println();
        tem.clear();
        for (int i = 0; i < nodes.size(); i++) {
            if (!nodes.get(i).checkTautology()){
                tem.add(i);
            }
        }
        if (!tem.isEmpty()){
            System.out.println(String.format("\nFind these formulas can't be proved:"));
            for (int i = 0; i < tem.size(); i++) {
                System.out.println(nodes.get(tem.get(i)));
            }
            return ;
        }

    }

}
