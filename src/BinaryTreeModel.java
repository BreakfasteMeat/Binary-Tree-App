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
    public void checkTreeParent(){
        if(!tree.checkParent(tree.getRoot())){
            System.out.println("Something went wrong with parent node relationship!");
        }
    }
    public void insert(int value) {
        tree.insert(value);
        checkTreeParent();
    }
    public void delete(int value) {
        tree.remove(value);
        checkTreeParent();
    }
}
