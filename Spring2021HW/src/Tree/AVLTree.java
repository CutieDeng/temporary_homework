package Tree;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * AVL Tree is an automatic-balanced binary search tree. <p>
 *
 * This AVL Tree is a tentative try to realize the nature like auto-balanced. <p>
 *
 * Three are only two constructors for you to create a special AVL Tree, which contains
 * nonparametric constructor and comparator-parametric constructor. <p>
 *
 * Nonparametric constructor: it's an unsafe constructor, it won't define the relationship of the elements
 * at once in the tree. If you don't call the method {@link AVLTree#setComparator(Comparator)} to make clear
 * the comparator, it may throw {@link RuntimeException} when the tree cannot truly compare the elements in the tree.
 * <p>
 *
 * Comparator-parametric constructor: it's a strict constructor, which means you need to give a proper comparator
 *  as the parameter. If you give a null comparator, it would leads to a {@link RuntimeException} as the information:
 *  <i>Deception argument for constructor.</i>
 *  <p>
 *
 *  There are only eight methods you can use in it. I'd like to introduce them one by one.
 *  <p>
 *
 * {@link AVLTree#setComparator(Comparator)} If you forget to set a comparator when you create the AVLTree unfortunately,
 * or you want to change the comparator suddenly, you can use this method to change the comparator of the tree.
 * Believe me, if you abuse this method, you can easily make the AVL Tree confused and eventually get in chaos.
 * <p>
 *
 * {@link AVLTree#insert(Object)} You can use this method to add an object in the tree. If you don't want the tree produces
 * any bug or other expected situations, I suggest you not to make use of <strong>reflect</strong> and <strong>thread</strong>
 *  because I actually don't prepare something to prevent these situations to happen. Meanwhile, owing to the way that
 *  object would input in the tree is value transmission, don't make use of the change transmission or you would soon meet
 *  quite a lot of bugs.
 *  <p>
 *
 *  {@link AVLTree#delete(Object)} The method would delete the same value in the tree. The equivalence won't use the
 *  {@link Object#equals(Object)} but use the {@link Comparator#compare(Object, Object)} to confirm the equivalent of the
 *  different objects, which means, if you say that, compare(A, B) would get an integer zero, I would regard them equivalent.
 *  Similarly, these methods are not secure in multi-thread.
 *  <p>
 *
 *  {@link AVLTree#search(Object)} This method would find the value in the tree. If this value is in the tree, it would return
 *  true and return false if not.
 *  <p>
 *
 *  {@link AVLTree#searchPrecursor(Object)} This method would find the object which is little less than the value you give as
 *  parameter. We always give it a name as "precursor".
 *  <p>
 *
 *  {@link AVLTree#searchSucceeding(Object)} Find the "succeeding" of the value you give.
 *  <p>
 *
 *  {@link AVLTree#getSize()} It would returns the size of the tree, which means the number of the data stored in the tree.
 *  <p>
 *
 *  {@link AVLTree#clear()} It would delete all information in the AVL Tree.
 *
 *  {@link AVLTree#toArrayList()} It would get all information in the AVL Tree in the arrayList form.
 *
 * @param <T> The type of the elements.
 * @see ArrayList
 * @see Comparator
 * @version 2021-04-28
 * @author Cutie
 */

@SuppressWarnings("all")
public class AVLTree<T> {

    /**
     * The number of the node in the AVL tree.
     */
    private int size = 0;

    /**
     * The root node of the AVL tree.
     * <p>
     *
     * If the tree is initialized just now, or you have deleted all nodes in the tree,
     * it would be null.
     * <p>
     *
     * The access is <i>private</i>, which means you can't get it directly.
     */
    private Decorator root = null;

    /**
     * The comparator of the AVL Tree.
     * <p>
     *
     * The tree would automatically use the comparator to compare the values you plug in the tree.
     * <p>
     *
     * If you change the comparator of it halfway, you would destroy the strict structure of the tree.
     * <p>
     *
     * I won't give a proper method to allow it to happen.
     */
    private Comparator<T> comparator = null;

    /**
     * This Non-parametric constructor can create a null AVL Tree.
     * <p>
     *
     * However, it doesn't give a proper comparator when initialization.
     * <p>
     *
     * If you determine to use it, you have to make the element implementing
     * the comparator.
     * <p>
     *
     * @deprecated This method is unsafe but it won't throw an exception just now but when running
     * in process.
     */
    @Deprecated
    public AVLTree() {
    }

    /**
     * True - It means the null value is existed in the AVL Tree.
     */
    private boolean nullExisted = false;

    /**
     * This comparator-parametric constructor needs a comparator as a parameter.
     * <p>
     *
     * Don't give a null value as the comparator to cheat the constructor, or it would
     * soon give an exception as result.
     * @param comparator The comparator to compare the values in the tree.
     * @throws RuntimeException When you give a null comparator to cheat the tree.
     */
    public AVLTree(Comparator<T> comparator) {
        if (comparator == null){
            throw new RuntimeException("Deception argument for constructor.");
        }
        this.comparator = comparator;
    }

    /**
     * This modifier method can set the comparator of the AVL Tree when it still hasn't a comparator.
     * @param comparator The comparator you want to set in the AVL Tree.
     * @return The AVL Tree itself.
     * @throws RuntimeException When you gives a null comparator to cheat it, or it has
     * already used a comparator.
     */
    public AVLTree<T> setComparator(Comparator<T> comparator){
        if (comparator == null){
            throw new RuntimeException("Deception comparator to set.");
        }
        if (this.comparator != null){
            throw new RuntimeException("Ban changing the comparator unsafely.");
        }
        this.comparator = comparator;
        return this;
    }

    /**
     * This modifier method would insert the value in the AVL Tree.
     * @param value The value you want to insert in the tree.
     * @return True if the value has been added successfully, false if else.
     */
    public boolean insert(T value){
        if (value == null){
            if (nullExisted){
                return false;
            }
            nullExisted = true;
            size ++;
            return true;
        }
        if (root == null){
            root = new Decorator(value);
            size ++;
            return true;
        }
        else {
            if (root.addValue(value)){
                size ++;
                return true;
            }
            else{
                return false;
            }
        }
    }

    /**
     * The accessor method would transpose the information in the AVL Tree into a {@link String}.
     * @return The information in the tree expanding from small to large.
     */
    @Override
    public String toString(){
        if (root == null){
            return "null";
        }
        String name = "";
        if (nullExisted){
            name = "NULL\n";
        }
        return name + root.getString();
    }

    /**
     * This modifier method would delete the value you ask in the AVL Tree.
     * @param value The value you want to delete in the AVL Tree.
     * @return True if you delete it successfully, false if else.
     */
    public boolean delete(T value){
        if (value == null){
            if (nullExisted){
                nullExisted = false;
                size --;
                return true;
            }
            return false;
        }
        if (root == null){
            return false;
        }
        Decorator decorator = root.search(value);
        if (decorator == null){
            return false;
        }
        if (decorator.getChild() < 3){
            root.crazyRecursiveDelete(decorator);
        }
        else{
            Decorator next = root.searchPrecursor(value);
            if (next == null){
                next = root.searchSucceeding(value);
            }
            if (next == null){
                return false;
            }
            root.crazyRecursiveDelete(next);
            decorator.value = next.value;
        }
        size --;
        return true;
    }

    /**
     * This accessor method would attempt to search the value in the AVL Tree.
     * @param value The value you ask whether it's in the tree or not.
     * @return True if it exists in the AVL Tree, false if else.
     */
    public boolean search(T value){
        if (value == null){
            return nullExisted;
        }
        if (root == null){
            return false;
        }
        return root.search(value) != null;
    }

    /**
     * The accessor method would find the value less than the value you ask.
     * <p>
     *
     * If you ask the value has existed in the AVL Tree, it wouldn't return the
     * same value back, but the less value back.
     * @param value The value you ask in the AVL Tree.
     * @return The largest value in the values which are less than the value you ask.
     */
    public T searchPrecursor(T value){
        if (value == null){
            return null;
        }
        if (root == null){
            return null;
        }
        try {
            return root.searchPrecursor(value).value;
        }
        catch (NullPointerException nullPointerException){
            return null;
        }

    }

    /**
     * The accessor method would find the value a little more than the value you ask.
     * <p>
     *
     * If you ask the value has existed in the AVL Tree, it wouldn't return the same value back,
     * but the a little larger value back.
     * @param value The value you ask in the AVL Tree.
     * @return The smallest value in the values which are larger than the value you ask.
     */
    public T searchSucceeding(T value){
        if (value == null){
            return null;
        }
        if (root == null){
            return null;
        }
        try {
            return root.searchSucceeding(value).value;
        }
        catch (NullPointerException nullPointerException){
            return null;
        }
    }

    /**
     * The form of the information in the AVL Tree.
     */
    private class Decorator{
        private T value;
        private Decorator father = null;
        private Decorator leftChild = null;
        private Decorator rightChild = null;
        private int height = 1;

        private Decorator(T value) {
            this.value = value;
        }

        public Decorator searchPrecursor(T value){
            int c = comparator.compare(this.value, value);
            if (c >= 0){
                if (leftChild == null){
                    return null;
                }
                return leftChild.searchPrecursor(value);
            }
            Decorator decorator = null;
            if (rightChild != null){
                decorator = rightChild.searchPrecursor(value);
            }
            return (decorator == null) ? this : decorator;
        }

        public Decorator searchSucceeding(T value){
            int c = comparator.compare(this.value, value);
            if (c <= 0){
                if (rightChild == null){
                    return null;
                }
                return rightChild.searchSucceeding(value);
            }
            Decorator decorator = null;
            if (leftChild != null){
                decorator = leftChild.searchSucceeding(value);
            }
            return (decorator == null) ? this : decorator;
        }

        public Decorator search(T value){
            int c = comparator.compare(this.value, value);
            if (c == 0){
                return this;
            }
            if (c > 0){
                if (leftChild == null){
                    return null;
                }
                return leftChild.search(value);
            }
            if (rightChild == null){
                return null;
            }
            return rightChild.search(value);
        }

        private void ergodic(ArrayList<T> list){
            if (this.leftChild != null){
                this.leftChild.ergodic(list);
            }
            list.add(this.value);
            if (this.rightChild != null){
                this.rightChild.ergodic(list);
            }
        }

        public boolean addValue(T value){
            Decorator decorator = new Decorator(value);
            if (root == null){
                root = decorator;
                return true;
            }
            return root.recursiveInsert(decorator);
        }

        private void crazyRecursiveDelete(Decorator toDelete){
            int c = comparator.compare(this.value, toDelete.value);
            if (c == 0){
                this.unsafeDelete();
                return ;
            }
            if (c > 0){
                if (this.leftChild == null){
                    return ;
                }
                this.leftChild.crazyRecursiveDelete(toDelete);
            }
            else {
                if (this.rightChild == null){
                    return ;
                }
                this.rightChild.crazyRecursiveDelete(toDelete);
            }
            this.fix();
        }

        private void unsafeDelete(){
            if (this.rightChild == null && this.leftChild != null){
                if (this.father != null){
                    if (this.father.leftChild == this){
                        this.father.leftChild = this.leftChild;
                    }
                    else if (this.father.rightChild == this){
                        this.father.rightChild = this.leftChild;
                    }
                    else{
                        throw new RuntimeException("Illegitimate child: " + this);
                    }
                }
                else if (root == this){
                    root = this.leftChild;
                }
                else {
                    throw new RuntimeException("Unexpected point to delete: " + this);
                }
                return ;
            }

            if (this.leftChild == null && this.rightChild != null){
                if (this.father != null){
                    if (this.father.leftChild == this){
                        this.father.leftChild = this.rightChild;
                    }
                    else if (this.father.rightChild == this){
                        this.father.rightChild = this.rightChild;
                    }
                    else{
                        throw new RuntimeException("Illegitimate child: " + this);
                    }
                }
                else if (root == this){
                    root = this.rightChild;
                }
                else {
                    throw new RuntimeException("Unexpected point to delete: "+ this);
                }
                return ;
            }

            if (this.leftChild != null){
                throw new RuntimeException("Cannot delete the point safely: " + this);
            }

            if (this.father != null){
                if (this.father.leftChild == this){
                    this.father.leftChild = null;
                }
                else if (this.father.rightChild == this){
                    this.father.rightChild = null;
                }
                else{
                    throw new RuntimeException("Illegitimate child: " + this);
                }
            }
        }

        private boolean recursiveInsert(Decorator waitForInsert){
            if (comparator == null){
                comparator = (Comparator<T>) this.value;
            }
            int c = comparator.compare(this.value, waitForInsert.value);
            if (c == 0){
                return false;
            }
            boolean ans;
            if (c > 0){
                if (this.leftChild == null){
                    this.leftChild = waitForInsert;
                    waitForInsert.father = this;
                    ans = true;
                }
                else{
                    ans = this.leftChild.recursiveInsert(waitForInsert);
                }
            }
            else{
                if (this.rightChild == null){
                    this.rightChild = waitForInsert;
                    waitForInsert.father = this;
                    ans = true;
                }
                else{
                    ans = this.rightChild.recursiveInsert(waitForInsert);
                }
            }
            if (!ans){
                return false;
            }
            this.fix();
            return true;
        }

        private void fix(){
            this.updateHeight();
            if (Math.abs(this.compare()) <= 1){
                return ;
            }
            if (this.compare() >= 2){
                int i = this.rightChild.compare();
                if (i < 0){
                    this.rightChild.rightTurn();
                }
                this.leftTurn();
            }
            else if (this.compare() <= -2){
                int i = this.leftChild.compare();
                if (i > 0){
                    this.leftChild.leftTurn();
                }
                this.rightTurn();
            }
        }

        private void updateHeight(){
            int maximum = 0;
            if (leftChild != null){
                maximum = leftChild.height;
            }
            if (rightChild != null){
                maximum = Math.max(rightChild.height, maximum);
            }
            this.height = maximum + 1;
        }

        private void rightTurn(){
            if (this.father == null){
                if (root != this){
                    throw new RuntimeException("Cannot find the father of the node: " + this);
                }
                root = this.leftChild;
            }
            else{
                if (this.father.rightChild == this){
                    this.father.rightChild = this.leftChild;
                }
                else{
                    if (this.father.leftChild == this){
                        this.father.leftChild = this.leftChild;
                    }
                    else{
                        throw new RuntimeException("Illegitimate child node.");
                    }
                }
            }

            this.leftChild.father = this.father;
            this.father = this.leftChild;
            Decorator temporaryDecorator = this.leftChild.rightChild;
            this.leftChild.rightChild = this;
            this.leftChild = temporaryDecorator;
            if (temporaryDecorator != null){
                temporaryDecorator.father = this;
            }
            this.updateHeight();
            this.father.updateHeight();
        }

        private void leftTurn(){
            if (this.father == null){
                if (root != this){
                    throw new RuntimeException("Cannot find the father of the node: " + this);
                }
                root = this.rightChild;
            }
            else{
                if (this.father.rightChild == this){
                    this.father.rightChild = this.rightChild;
                }
                else{
                    if (this.father.leftChild == this){
                        this.father.leftChild = this.rightChild;
                    }
                    else{
                        throw new RuntimeException("Illegitimate child node.");
                    }
                }
            }
            this.rightChild.father = this.father;
            this.father = this.rightChild;
            Decorator temporaryDecorator = this.rightChild.leftChild;
            this.rightChild.leftChild = this;
            this.rightChild = temporaryDecorator;
            if (temporaryDecorator != null){
                temporaryDecorator.father = this;
            }
            this.updateHeight();
            this.father.updateHeight();
        }

        private int compare(){
            int l = 0;
            if (this.leftChild != null){
                l = this.leftChild.height;
            }
            int r = 0;
            if (this.rightChild != null){
                r = this.rightChild.height;
            }
            return r - l;
        }

        @Override
        public String toString(){
            StringBuilder builder = new StringBuilder("( value : " + value);
            try {
                builder.append(" father : " + father.value);
            }
            catch (NullPointerException nullPointerException){
            }
            try {
                builder.append(" left child : " + leftChild.value);
            }
            catch (NullPointerException nullPointerException){
            }
            try {
                builder.append(" right child : " + rightChild.value);
            }
            catch (NullPointerException nullPointerException){
            }
            builder.append(" height : " + this.height);
            builder.append(" )");
            return builder.toString();
        }

        public String getString(){
            StringBuilder stringBuilder = new StringBuilder();
            generateString(stringBuilder);
            return stringBuilder.toString();
        }

        private void generateString(StringBuilder builder){
            if (this.leftChild != null){
                this.leftChild.generateString(builder);
            }
            builder.append(this.value + "\n");
            if (this.rightChild != null){
                this.rightChild.generateString(builder);
            }
        }

        private int getChild(){
            int ans = 0;
            if (leftChild != null){
                ans += 2;
            }
            if (rightChild != null){
                ans += 1;
            }
            return ans;
        }

    }

    /**
     * @return The number of the nodes in the AVL Tree.
     */
    public int getSize(){
        return size;
    }

    /**
     * Clear the total nodes in the AVL Tree.
     */
    public void clear(){
        root = null;
        size = 0;
        nullExisted = false;
    }

    /**
     * The accessor method would transpose the AVL Tree to an array list.
     * <p>
     *
     * If the root is null, we still can get an empty ArrayList but not a null pointer.
     * <p>
     *
     * If you have plugged in the null value in the AVL Tree already, you would get the null value in the
     * list at the beginning of the list.
     *
     * @return The arraylist in order to store the all information in the AVL Tree.
     */
    public ArrayList<T> toArrayList(){
        ArrayList<T> list = new ArrayList<>();
        if (nullExisted){
            list.add(null);
        }
        if (root != null){
            root.ergodic(list);
        }
        return list;
    }
}
