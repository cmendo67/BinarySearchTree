/**
 *
 * Name: Carlos Mendoza
 * Description:For this assignment we will be implementing a version of a Binary Search Tree.
 */package BinarySearchTree.doc;

/**
 * This class throws an exception if a given node does not have Siblings.
 */
public class NoSiblingsException extends Throwable {
    public NoSiblingsException() {}

    public String toString(){
        return "EXCEPTION: NODE DOES NOT HAVE A SIBLING!";
    }
}
