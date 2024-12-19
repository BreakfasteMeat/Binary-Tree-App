
public class BinaryTree{
    private Node root;
    private int size;

    public BinaryTree(){
        size = 0;
    }

    private Node insert_recursive(int elem, Node node){
        if(node == null){
            size++;
            return new Node(elem);
        } else if(node.elem > elem){
            node.left = insert_recursive(elem, node.left);
            node.left.parent = node;

        } else if(node.elem < elem){
            node.right = insert_recursive(elem, node.right);
            node.right.parent = node;

        }
        return node;
    }
    private Node findMin(Node node){
        while(node.left != null){
            node = node.left;
        }
        return node;
    }
    private Node getSuccessor(Node node){
        return findMin(node.right);
    }
    private Node findValue(int elem, Node node){
        if(node == null){
            return null;
        }
        if(elem < node.elem){
            return findValue(elem, node.left);
        } else if(node.elem < elem){
            return findValue(elem, node.right);
        } else {
            return node;
        }
    }
    private Node remove_helper(Node node, int elem){
        if (elem < node.elem) {
            // Recur to the left subtree
            node.left = remove_helper(node.left, elem);
        } else if (elem > node.elem) {
            // Recur to the right subtree
            node.right = remove_helper(node.right, elem);
        } else {
            // Node to be removed found
            if (node.left != null && node.right != null) {
                // Case 1: Node with two children
                Node successor = findMin(node.right); // Find in-order successor
                node.elem = successor.elem; // Replace with successor's value
                node.right = remove_helper(node.right, successor.elem); // Remove successor
            } else if (node.left != null) {
                // Case 2: Node with only left child
                return node.left; // Replace node with left child
            } else if (node.right != null) {
                // Case 3: Node with only right child
                return node.right; // Replace node with right child
            } else {
                // Case 4: Leaf node
                return null; // Remove the node
            }
        }
        return node;
    }
    public void setAllToNotVisited(){
        setAllToNotVisited_recursive(root);
    }
    private void setAllToNotVisited_recursive(Node node){
        node.visited = false;
        if(node.left != null){
            setAllToNotVisited_recursive(node.left);
        }
        if(node.right != null){
            setAllToNotVisited_recursive(node.right);
        }
    }


    public void setRoot(int elem) {
        this.root = new Node(elem);
    }
    public Node getRoot(){
        return this.root;
    }
    //Insert Value in BST
    public void insert(int elem){
        root = insert_recursive(elem, root);
        root.parent = null;
    }

    public void remove(int elem){
        root = remove_helper(root,elem);
    }




    //DEBUG PRINTING
    void printTree() {
        printTree("", root, false);
    }
    void printTree(String prefix, Node n, boolean isLeft) {
        System.out.print(prefix);
        System.out.print(isLeft ? "+--L: " : "+--R: ");
        System.out.println(n.elem);
        if (n.left != null) {
            printTree(prefix + "|  ", n.left, true);
        }
        if (n.right != null) {
            printTree(prefix + "|  ", n.right, false);
        }
    }
    public boolean checkParent(Node node) {
        if (node == null) {
            return true;
        }

        if (node == root && node.parent != null) {
            return false;
        }

        if (node.left != null && node.left.parent != node) {
            return false;
        }

        if (node.right != null && node.right.parent != node) {
            return false;
        }
        return checkParent(node.left) && checkParent(node.right);
    }


}
