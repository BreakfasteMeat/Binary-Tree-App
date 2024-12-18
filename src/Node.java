public class Node {
    int elem;
    Node left;
    Node right;
    boolean visited;
    public Node(int elem) {
        this.elem = elem;
        visited = false;
        left = right = null;
    }

}
