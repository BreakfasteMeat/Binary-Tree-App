
public class BinaryTree{
    private Node root;
    private int size;
    boolean isAVL;

    public BinaryTree(){
        isAVL = false;
        size = 0;
    }

    public void setAVL(boolean AVL) {
        isAVL = AVL;
    }

    public boolean search(int key){
        return search_recrusive(key,root);
    }
    private boolean search_recrusive(int key, Node n){
        if(n == null){
            return false;
        } else if(key > n.elem){
            return search_recrusive(key,n.right);
        } else if(key < n.elem){
            return search_recrusive(key,n.left);
        } else if(key == n.elem){
            return true;
        }
        return false;
    }

    private Node insert_recursive(int elem, Node node){
        Node n = null;
        if(node == null){
            size++;
            n = new Node(elem);
        } else if(node.elem > elem){
            node.left = insert_recursive(elem, node.left);
            node.left.parent = node;
            n = node;

        } else if(node.elem < elem){
            node.right = insert_recursive(elem, node.right);
            node.right.parent = node;
            n = node;
        }
        if(!isAVL) {
            return n;
        }


        //AVL CODE
        int balance;
        if(node == null) {
            return n;
        }
        balance = node.getBalance();
        if(balance < -1 && elem > node.right.elem){
            n = LRotate(node);
        } else if(balance > 1 && elem < node.left.elem){
            n = RRotate(node);
        } else if(balance < -1 && elem < node.right.elem){
            node.right = RRotate(node.right);
            n = LRotate(node);
        } else if(balance > 1 && elem > node.left.elem){
            node.left = LRotate(node.left);
            n = RRotate(node);
        }


        return n;
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
            node.left = remove_helper(node.left, elem);
        } else if (elem > node.elem) {
            node.right = remove_helper(node.right, elem);
        } else {
            if (node.left != null && node.right != null) {
                Node successor = findMin(node.right); // Find in-order successor
                node.elem = successor.elem; // Replace with successor's value
                node.right = remove_helper(node.right, successor.elem); // Remove successor
            } else if (node.left != null) {
                return node.left; // Replace node with left child
            } else if (node.right != null) {
                return node.right;
            } else {
                return null;
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
            System.out.println("Root-parent relationship bonkers!");
            return false;
        }

        if (node.left != null && node.left.parent != node) {
            System.out.println(node.left.elem + " parent is not " + node.elem + " but is " + node.left.parent.elem);
            return false;
        }

        if (node.right != null && node.right.parent != node) {
            System.out.println(node.right.elem + " parent is not " + node.elem + " but is " + node.right.parent.elem);
            return false;
        }
        return checkParent(node.left) && checkParent(node.right);
    }

    public Node LRotate(Node node){
        Node subTree1 = node.right.left;
        Node right = node.right;
        node.parent = right;
        node.right.left = node;
        node.right = subTree1;
        if(subTree1 != null){
            subTree1.parent = node;
        }

        return right;
    }
    public Node RRotate(Node node){
        Node subTree1 = node.left.right;
        Node left = node.left;
        node.parent = left;
        node.left.right = node;
        node.left = subTree1;
        if(subTree1 != null){
            subTree1.parent = node;
        }
        return left;
    }


}
