public class BinaryTreeModel {
    private BinaryTree tree;
    public BinaryTreeModel() {
        this.tree = new BinaryTree();
    }

    public BinaryTree getTree() {
        return tree;
    }

    public void clearTree(){
        boolean isAVL = tree.isAVL;
        tree = new BinaryTree();
        tree.setAVL(isAVL);
    }
    public boolean search_value(int value){
        return tree.search(value);
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
