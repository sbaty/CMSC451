/**
 * Filename:    BST
 * Author:      Shafro Batyrov
/ * Date:        7/1/2018
 * Description: This class represents a generic BST, constructs and returns a String of sorted node order.
 */
class BST<T extends Comparable<T>> {

    // Variables
    private BSTnode root;
    private StringBuilder result = new StringBuilder();

    //Constructing BST
    BST() {
        root = null;
        result.setLength(0);   
    }

    //If root is empty, new value is root. Otherwise, call insertNodeRecursive (current node, root node)

    void insertNode (T value) {
        if (root == null) {                 
            root = new BSTnode(value);      
            return;
        }
        insertNodeRecursive(value, root);
    }

     // Determines where each node will be placed in BST
   
    private void insertNodeRecursive(T value, BSTnode node) {
        if (value.compareTo(node.value) <= 0) {         // if current node is <= previous node
            if (node.left != null) {                    // and left node is not null
                insertNodeRecursive(value, node.left);  // recurse with new values (current node, left node)
            } else {                                    // else insert current node on left
                node.left = new BSTnode(value);
            }
        } else if (value.compareTo(node.value) > 0) {   // if current node is > previous node
            if (node.right !=null) {                    // and right node is not null
                insertNodeRecursive(value, node.right); // recurse with new values (current node, right node)
            } else {                                    // else insert current node on right
                node.right = new BSTnode(value);
            }
        }
    }

   //Returns Ascending Sort order of list as a String
     
    String getAscending() {
        inorderTraversal(root);
        return result.toString();
    }

    //Reverses Order of getAscending() and returns list in string form
     
    String getDescending() {
        getAscending();

        // Splits Ascending into array of numbers
        String[] numbers = result.toString().split(" ");   // result string is put into an array
        result.setLength(0);    // resets result String
        int len = numbers.length;  // gets length of array

        // iterates over array to reverse order of numbers
        for (int i = len - 1; i >= 0; i--) {
            result.append(numbers[i]).append(" ");
        }
        return result.toString();
    }

    // Builds a String of the inorder traversal of the BST 
    private void inorderTraversal(BSTnode root) {
        if (root.value != null) {
            if (root.getLeft() != null) inorderTraversal(root.getLeft());   
            String res = (root.value).toString();                          
            result.append(res).append(" ");
            if (root.getRight() != null) inorderTraversal(root.getRight()); 
        }
    }

    // Defines Node in BST

    class BSTnode {

        // Variables
        private T value;
        private BSTnode left, right;

        BSTnode(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        BSTnode getLeft() {
            return left;
        }

        BSTnode getRight() {
            return right;
        }
    }
}