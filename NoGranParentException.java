/**
 *
 * Name: Carlos Mendoza
 * Description:For this assignment we will be implementing a version of a Binary Search Tree.
 */
package BinarySearchTree.doc;

/**
 * This class throws an exception if the a given node from a tree does not have a GrandParent.
 */
public class NoGranParentException extends Throwable{
public NoGranParentException() {}

//To String()
public String toString(){
        return "EXCEPTION: NODE DOES NOT HAVE A GRANDPARENT!";
        }
}
