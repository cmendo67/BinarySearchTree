/**
 *
 * Name: Carlos Mendoza
 * Description:For this assignment we will be implementing a version of a Binary Search Tree.
 */

package BinarySearchTree.doc;

import com.sun.source.tree.Tree;

import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Math.max;

/**
 * This class(BinarySearchTree) should be a generic class with the generic bounded by Comparable<E>. This class
 * has one data field which is root.Root is the root(head) of the tree. This class has a default constructor which will
 * initialize an empty tree and has a constructor that takes an array and uses the values in the array to populate the tree.
 * Values of the array are inserted into the tree in order from index 0 to n - 1.
 * This class also has helper methods and they are private.
 * </E>
 * @param <E> extends Comparable
 */
public class BinarySearchTree<E extends Comparable<E>> {

    //data fields
    private BstNode<E> root;//root of the tree

    //default constructor:Constructs an empty binary tree
    public BinarySearchTree() {
    }

    /**
     * Takes an array as an argument and uses the values in the array
     * to populate the tree.Array should be inserted into the tree in order from index
     * 0 to n-1.
     *
     * @param array
     */
    public BinarySearchTree(E[] array) {
        for (int i = 0; i < array.length; i++) {
            insert(array[i]);
        }
    }

    //getters
    public BstNode<E> getRoot() {

        return root;
    }

    //setters
    public void setRoot(BstNode<E> root) {
        this.root = root;
    }

    /**
     *
     * @param key
     * @return
     * @throws DuplicateItemException
     */
    private BstNode insertionPoint(E key) throws DuplicateItemException {
        BstNode current = this.root;
        BstNode parent = null;

        while (current != null) {
            if (key.compareTo((E) current.getData()) == 0) {
                throw new DuplicateItemException();
            } else if (key.compareTo((E) current.getData()) == -1) {
                parent = current;
                current = current.left;
            } else if (key.compareTo((E) current.getData()) == 1) {
                parent = current;
                current = current.right;
            }
        }
        return parent;
    }

    /**
     *The insert method adds a new value to the tree according to the rules of a binary search
     * tree.
     * @param key the item we want to add
     */
    public void insert(E key) {
        BstNode child = new BstNode(key);

        if (this.root == null) {
            this.root = child;
        } else {
            try {
                BstNode parent = insertionPoint(key);

                if (key.compareTo((E) parent.getData()) == -1) {
                    parent.left = child;
                    child.parent = parent;
                } else if (key.compareTo((E) parent.getData()) == 1) {
                    parent.right = child;
                    child.parent = parent;

                }
            } catch (DuplicateItemException ex) {
                System.out.println(ex.toString());
            }
        }
    }


    /**
     * The delete method takes a key(item) and removes the node from the tree according to the
     * rules of a binary search tree.
     * @param key the item we want to delete
     */
    public void delete(E key){
        delete2(nodeToDeleteKey(key));
    }

    /**
     * This method gets the data want to delete and returns the node to delete or null if the
     * node was not found.
     * @param key the item we want to delete
     * @return node of the key
     */
    private BstNode nodeToDeleteKey(E key) {

        BstNode current = this.root;

        while (current != null) {
            if (key.compareTo((E) current.getData()) == 0) {
                return current;
            } else if (key.compareTo((E) current.getData()) == -1) {
                    current = current.left;
            }
            else if(key.compareTo((E) current.getData())== 1){
                current = current.right;
            }
        }
        return null;
    }

    /**
     * This method checks if the node want to remove is a leaf node.It also checks if the item
     * we want to delete has only one child and it also checks if the element to delete has both children.
     * @param node we want to delete
     */
    private void delete2(BstNode node){

        if(isLeaf(node)) {
            if (isLeftChild(node)) {
                node.parent.left = null;
            } else if (isRightChild(node)) {
                node.parent.right = null;
            }
        }
        else if(numChildren(node) == 1){

                if(isLeftChild(node)){
                    BstNode child = node.left;
                    node.parent.left = child;
                    child.parent = grandParent(node).right;
                }
                else if(isRightChild(node)){

//                    BstNode child = node.left;
//                    child.parent = node.parent;
//                    node.parent.right = child;
                    BstNode child = node.right;
                    node.parent.right = child;
                    child.parent = grandParent(node).left;
                }
            }
        else if(numChildren(node) == 2){
                BstNode max = maxLeftSubTree(node);
                node.setData(max.getData());
                delete2(max);
            }
        }

    /**
     * The method(find(key)) method returns true or false depending on
     * if the key is found in the tree or not.
     *
     * @param key
     * @return true or false if key is found in the tree
     */
    public boolean find(E key) {

        BstNode current = this.root;

        //
        while (current != null) {
            if (key.compareTo((E) current.getData()) == 0) {
                return true;
            } else if (key.compareTo((E) current.getData()) == -1) {
                current = current.left;
            } else if (key.compareTo((E) current.getData()) == 1) {
                current = current.right;
            }
        }
        return false;
    }

    /**
     * This method(height()) returns the height of the tree and calls in the
     * heightHelperMethod().
     *
     * @return height the height of the tree
     */
    public int height() {
        //if root is empty: return 0
        if (this.root == null) {
            return 0;
        } else {
            //the height of the tree
            return heightHelperMthod(this.root);
        }
    }

    /**
     * This method is a helper method for height and takes in a BstNode from the parameter
     * and returns the height of the tree.
     * @param t is a BstNode from the tree
     * @return the height of the tree
     */
    private int heightHelperMthod(BstNode t){
        if(t == null){
            return 0;
        }
        else{
            return max(heightHelperMthod(t.left),heightHelperMthod(t.right)) + 1;
        }
    }
    /**
     * This method(iSEmpty) returns true or false if tree is empty
     * or not.
     *
     * @return true or false if tree is empty
     */
    public boolean isEmpty() {
        if (this.root == null) {
            return true;
        }
        return false;
    }

    /**
     * This method(isLeaf()) returns true or false if the node is a leaf or not.
     *
     * @param t
     * @return isLeaf
     */
    public boolean isLeaf(BstNode t) {

        int count = 0;

        if (t.left != null) {
            count++;
        }
        if (t.right != null) {
            count++;
        }

        if (count == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * The numChildren() method returns the number of children in a tree .
     * @param node a BstNode of tree
     * @return the number of children in a tree
     */
    private int numChildren(BstNode node){
        int count = 0;

        if(node.left != null){
            count++;
        }
        if(node.right != null){
            count++;
        }
        return count;
    }

    /**
     * THe maxLeftSubTree() method gives you the highest value of a node from the left child of the root.
     * @param node a BstNode of tree
     * @return the highest value from the left child of the root
     */
    private BstNode maxLeftSubTree(BstNode node){

        BstNode current = node.left;

        while(current.right != null){
         current = current.right;
        }

        return current;
    }



    /**
     * This method returns or false if the given node is a left child of its parent,
     * or not.
     *
     * @param node
     * @return isLeftChild(node)
     */
    public boolean isLeftChild(BstNode node) {
//        System.out.println(node.parent.left.getData());
        if(node.parent.left == null){
            return false;
        }
         if (node.parent.left.getData().compareTo(node.getData()) == 0) {
            return true;
        }
         else {
            return false;
        }
    }

    /**
     * This method returns or false if the given node is a right child of its parent,
     * or not.
     *
     * @param node
     * @return isRightChild(node)
     */
    public boolean isRightChild(BstNode node) {

        if(node.parent.right == null){
            return false;
        }
        if (node.parent.right.getData().compareTo(node.getData()) == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * The siblings() method returns the siblings node of the given node and takes in a node in the parameter.
     * @param node a BstNode of tree
     * @return sibling node of a tree
     */
    public BstNode siblings(BstNode node)  {

        try{
            if (isLeftChild(node)){
                if(node.parent.right == null){
                    return null;
                }
                if (node.parent.right != null){
                    return node.parent.right;
                }
                else{
                    throw new NoSiblingsException();
                }
            }
            else if(isRightChild(node)){
                if (node.parent.left != null){
                    return node.parent.left;
                }
                else{
                    throw new NoSiblingsException();
                }
            }
        }
        catch (NoSiblingsException ex){
            System.out.println(ex.toString());
        }
        return null;
    }

    /**
     * The uncle() method returns the uncle node of the given code and it also accepts a node
     * int the parameter of the method.
     * @param node a BstNode of tree
     * @return uncle node
     */
    public BstNode uncle(BstNode node){

        try{
                if(node.parent.parent == null){
                    throw new NoUncleException();
                }
                if(isLeftChild(node.parent)){
                    return node.parent.parent.right;
                }
                if(isRightChild(node.parent)){
                    return node.parent.parent.left;
                }
                if (isLeftChild(node.parent)) {
                    return node.parent.parent.right;
                }
                else if(isRightChild(node.parent)){
                    return node.parent.parent.left;
                }
                else {
                    throw new NoUncleException();
                }

        }
        catch (NoUncleException ex){
            System.out.println(ex.toString());
        }
        return null;

    }

    /**
     * The method grandParent() method returns the grandParent of the given node and also takes in a node
     * for the parameter.
     * @param node a BstNode of tree
     * @return grandParent node
     */
    public BstNode grandParent(BstNode node){

        try{

            if(node.parent.parent != null) {
                return node.parent.parent;
            }
            else {
                throw new NoGranParentException();
            }
        }
        catch (NoGranParentException ex){
            System.out.println(ex.toString());
        }
        return null;
    }


    public ArrayList<E> preorder2()
    {
        ArrayList<E> arrayList = new ArrayList<E>();
        preorder2(root, arrayList);
        return arrayList;

    }
    private void preorder2(BstNode<E> root, ArrayList<E> arrayList)
    {
        if (root == null) {
            return;
        }
        arrayList.add(root.getData());
        preorder2(root.left, arrayList);
        preorder2(root.right, arrayList);
    }

    /**
     * The method pre Order() returns an ArrayList of nodes generated using preOrder traversal.
     * @return ArrayList of nodes in preOrder
     */
    public ArrayList<BstNode> preOrder(){

        if(this.root == null){
            ArrayList<BstNode> arrayList = new ArrayList<>();
            return arrayList;
        }

        return preOrderHelperMethod(this.root);
    }

    /**
     * The method grandParent() method returns the grandParent of the given node and also takes in a node
     * for the parameter.
     * @param node a BstNode of tree
     * @return grandParent node
     */
    private ArrayList<BstNode> preOrderHelperMethod(BstNode node){

        ArrayList<BstNode> listPreOrder = new ArrayList<>();

        Stack<BstNode> nodeStack = new Stack<>();
        nodeStack.push(node);

        while (!nodeStack.isEmpty()){
           BstNode  current = nodeStack.pop();
             listPreOrder.add(current);

            if(current.right != null){
                nodeStack.push(current.right);
            }
            if(current.left != null){
                nodeStack.push(current.left);
            }
        }
        return listPreOrder;
    }

    public ArrayList<E> inorder2()
    {
        ArrayList<E> arrayList = new ArrayList<E>();
        inorder2(root, arrayList);
        return (ArrayList<E>) arrayList;
    }

    private void inorder2(BstNode<E> root, List<E> list)
    {
        if (root == null)
            return;
        inorder2(root.left, list);
        list.add(root.getData());
        inorder2(root.right, list);

    }

    /**
     * This method returns an ArrayList of nodes generated using inorder traversal.
     * @return ArrayList of nodes
     */
    public ArrayList<BstNode> inOrder(){

        if(this.root == null){
            ArrayList<BstNode> inOrderArrayList = new ArrayList<>();
            return inOrderArrayList;
        }

        return inOrderHelperMethod(this.root);
    }

    /**
     * This method returns an ArrayList of nodes generated using inorder traversal.
     * @param node a BstNode of tree
     * @return ArrayList of nodes
     */
    private ArrayList<BstNode> inOrderHelperMethod(BstNode node){
        ArrayList<BstNode> listInOrder = new ArrayList<>();

        Stack<BstNode> inOrderStack= new Stack<>();
        BstNode current = node;

        while (!inOrderStack.isEmpty() || current != null){
//            listInOrder.add( current.getData());

            if(current != null){
                inOrderStack.push(current);
                current = current.left;
            }
            else{
                current = inOrderStack.pop();
                listInOrder.add(current);
                current = current.right;
            }
        }
        return listInOrder;
    }

    public java.util.List<E> postorder2()
    {
        ArrayList<E> arrayList = new ArrayList<E>();
        postorder2(root, arrayList);
        return arrayList;
    }
    private void postorder2(BstNode<E> root, ArrayList<E> arrayList)
    {
        if (root == null) {
            return;
        }
        postorder2(root.left, arrayList);
        postorder2(root.right, arrayList);
        arrayList.add(root.getData());
    }

    /**
     * *This method returns an ArrayList of nodes generated using postOrder traversal.
     * @return ArrayList of nodes
     */
    public ArrayList<BstNode> postOrder(){

        if(this.root == null){
            ArrayList<BstNode> postOrderArrayList = new ArrayList<>();
            return postOrderArrayList;
        }

        return postOrderHelperMethod(this.root);
    }

    /**
     *This method returns an ArrayList of nodes generated using postOrder traversal.
     * @param node a BstNode of tree
     * @return ArrayList of nodes
     */
    private ArrayList<BstNode> postOrderHelperMethod(BstNode node){
        ArrayList<BstNode> postOrderList = new ArrayList<>();

        Stack<BstNode> postOrderStack= new Stack<>();
        Stack<BstNode> postOrderStack2 = new Stack<>();

        postOrderStack.push(node);

        while (!postOrderStack.isEmpty()){
                BstNode current = postOrderStack.pop();
                postOrderStack2.push(current);
            if(current.left != null){
                postOrderStack.push(current.left);
            }
            if(current.right != null){
               postOrderStack.push(current.right);
            }
        }
        while(!postOrderStack2.isEmpty()){
            BstNode current = postOrderStack2.pop();
            postOrderList.add(current);
        }
        return postOrderList;
    }

    /**
     * This method breadthFirst() returns an arrayList of nodes generated using breadthFirst traversal.
     * @return ArrayList of nodes
     */
        public ArrayList<E> breadthFirst2() {

            ArrayList<E> breadthList = new ArrayList<>();
            Queue<BstNode> queueList = new LinkedList<>();

            queueList.add(this.root);

            while (!queueList.isEmpty()) {
                BstNode node = queueList.remove();
                breadthList.add((E) node.getData());

                if (node.left != null) {
                    queueList.add(node.left);
                }
                if (node.right != null) {
                    queueList.add(node.right);
                }
            }
            return breadthList;
        }

    /**
     * This method breadthFirst() returns an arrayList of nodes generated using breadthFirst traversal.
     * @return ArrayList of nodes
     */
    public ArrayList<BstNode> breadthFirst() {

        ArrayList<BstNode> breadthList = new ArrayList<>();
        Queue<BstNode> queueList = new LinkedList<>();

        queueList.add(this.root);

        while (!queueList.isEmpty()) {
            BstNode node = queueList.remove();
            breadthList.add(node);

            if (node.left != null) {
                queueList.add(node.left);
            }
            if (node.right != null) {
                queueList.add(node.right);
            }
        }
        return breadthList;
    }

    private boolean searchTree(BstNode<E> root, E data){
        if(root == null) {
            return false;
        }
        else if(data.compareTo(root.getData()) == 0) {
            return true;
        }
        else{
            if(data.compareTo(root.getData()) > 0) {
                return searchTree(root.right, data);
            }
            else {
                return searchTree(root.left, data);
            }
        }
    }

    public boolean searchTree(E data){
        return searchTree(root, data);
    }
}