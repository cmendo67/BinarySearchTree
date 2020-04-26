/**
 *
 * Name: Carlos Mendoza
 * Description:For this assignment we will be implementing a version of a Binary Search Tree.
 */
package BinarySearchTree.doc;

/**
 * This class is a generic class that extends comparable. This class has BstNode references called
 * parent, left, and right in order to traverse through tree. It will point to the parent
 *, left child, and right child. This class should also have a field called data which will store
 * the data of the node.
 * @param <E> extends Comparable
 */
public class BstNode<E extends Comparable<E>> {

    /**
     * Data fields for the class BstNode<E>
     * The data Fields are parent, left, right, and data.
     */
    public BstNode<E> parent;//a reference to the parent node
    public BstNode<E> left;//a reference to the left child
    public BstNode<E> right;//a reference to the right child
    private E data;//an element stored at this node

    /**
     * Default constructor
     */
    public BstNode(){
    }
    /**
     * Constructor that takes a value for data and initializes data
     * constructs a node with the given elements and neighbors
     */
    public BstNode(E data){
        this.data = data;
    }
    //getters(accessors methods)
    public E getData() {
        return data;
    }
    //setters(update methods)
    public void setData(E data) {
        this.data = data;
    }
}
