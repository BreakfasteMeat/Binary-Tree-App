public class Node {
    int elem;
    Node left;
    Node right;
    Node parent;
    boolean visited;
    public Node(int elem) {
        this.elem = elem;
        visited = false;
        left = right = parent = null;
    }
    public int height(){
        int lHeight = -1;
        int rHeight = -1;
        if(this == null){
            return 0;
        }
        if(left != null){
            lHeight = left.height();
        }
        if(right != null){
            rHeight = right.height();
        }
        return 1 + Math.max(lHeight, rHeight);
    }

    public int depth(){
        if(parent == null){
            return 0;
        }
        return 1 + parent.depth();
    }
    public int getBalance(){
        int lHeight = -1;
        int rHeight = -1;
        if(this == null){
            return 0;
        }
        if(left != null){
            lHeight = left.height();
        }
        if(right != null){
            rHeight = right.height();
        }
        return lHeight - rHeight;
    }

}
