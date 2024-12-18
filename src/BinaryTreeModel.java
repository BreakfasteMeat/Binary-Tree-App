public class BinaryTreeModel {
    private BinaryTree tree;
    public BinaryTreeModel() {
        this.tree = new BinaryTree();
    }

    public BinaryTree getTree() {
        return tree;
    }

    public void clearTree(){
        tree = new BinaryTree();
    }
    public void insert(int value) {
        tree.insert(value);
    }
    public void delete(int value) {
        tree.remove(value);
    }
}
