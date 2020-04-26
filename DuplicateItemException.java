/**
 *
 * Name: Carlos Mendoza
 * Description:For this assignment we will be implementing a version of a Binary Search Tree.
 */
package BinarySearchTree.doc;

/**
 * This class throws an exception if a tree has a the same value or the data of a node has a duplicate.
 */
public class DuplicateItemException extends Throwable {
    public DuplicateItemException() {}

        public String toString(){
            return "duplicate item errors in search tree insertions.";
        }
}
