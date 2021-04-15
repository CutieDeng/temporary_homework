package CPC4;

import java.util.ArrayList;

public class Node {

    private Node left = null;
    private Node right = null;

    private String name = null;
    private boolean value;

    public Node(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    public Node(Node right) {
        this.right = right;
    }

    public Node(String name) {
        this.name = name;
    }

    public boolean getValue() {
        if (left == null && right == null){
            return value;
        }
        if (left == null && right != null){
            return !right.getValue();
        }
        if (left != null && right == null){
            throw new RuntimeException("only have left leave node");
        }
        if (right.getValue() || !left.getValue()){
            return true;
        }
        return false;
    }

    public boolean setValue(boolean value) {
        if (left == null && right == null){
            this.value = value;
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        if (left == null && right == null && name != null){
            return String.format("%s", name);
        }
        if (left != null && right != null && name == null){
            return String.format("(%s -> %s)", left, right);
        }
        if (left == null && right != null){
            return String.format("(NOT %s)", right);
        }
        throw new RuntimeException("Can't get the name of the node.");
    }

    public void searchNode(ArrayList<Node> atomicNodes){
        if (atomicNodes.size() != 0){
            atomicNodes.clear();
        }
        recursionSearch(atomicNodes);
        return ;
    }

    private void recursionSearch(ArrayList<Node> atomicNodes){
        if (this.right == null){
            if (!atomicNodes.contains(this)){
                atomicNodes.add(this);
            }
        }
        if (this.left != null){
            this.left.recursionSearch(atomicNodes);
        }
        if (this.right != null){
            this.right.recursionSearch(atomicNodes);
        }
        return ;
    }

    public static void perform(ArrayList<Node> atomics, Runnable run){
        perform(atomics, 0, run);
    }

    private static void perform(ArrayList<Node> atomics, int k, Runnable run){
        if (k >= atomics.size()){
            run.run();
            return ;
        }
        atomics.get(k).setValue(true);
        perform(atomics, k+1, run);
        atomics.get(k).setValue(false);
        perform(atomics, k+1, run);
    }

    public boolean checkTautology(){
        ArrayList<Node> s = new ArrayList<>();
        this.searchNode(s);
        final boolean[] tem = {true};
        perform(s, ()->{
            if (!this.getValue()){
                tem[0] = false;
            }
        });
        return tem[0];
    }

    public int checkTheorem(){
        if (right == null){
            return 0;
        }
        if (left == null){
            return 0;
        }
        if (right.left != null && right.right != null && this.equals(right.right)){
            return 1;
        }
        if (left.left != null && left.right!=null && left.right.left != null && left.right.right!= null &&
                right.left != null && right.left.left != null && right.left.right != null &&
                right.right!=null && right.right.left != null && right.right.right != null &&
                left.left.equals(right.left.left) && left.left.equals(right.right.left) &&
                left.right.left.equals(right.left.right) && left.right.right.equals(right.right.right)){
            return 2;
        }
        if (left.left != null && left.left.left == null && left.left.right != null &&
                left.right != null && left.right.left == null && left.right.right != null &&
                right.left != null && right.right != null && left.left.right.equals(right.right) &&
                left.right.right.equals(right.left)){
            return 3;
        }
        return 0;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }
}
