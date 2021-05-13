package Tree;

import java.util.Comparator;
import java.util.Random;
import java.util.TreeMap;

public class TreeDemo {

    static Random random = new Random();
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>(Comparator.comparingInt(o -> o));
        for (int i = 0; i < 100; i++) {
            tree.insert(i);
        }
        tree.insert(null);
        System.out.println(tree.getSize());
//        tree.clear();
        System.out.println(tree.getSize());
        System.out.println(tree.search(null));
        System.out.println(tree.toArrayList());
    }
}
