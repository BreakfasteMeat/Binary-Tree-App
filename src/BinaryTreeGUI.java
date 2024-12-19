import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTreeGUI extends JPanel{
    private BinaryTreeModel model;
    private JLabel label;
    public BinaryTreeGUI(){
        model = new BinaryTreeModel();
        label = new JLabel();
        Font font = new Font("Arial", Font.BOLD, 20);
        label.setText("");
        label.setFont(font);
        label.setForeground(AppSettings.fontColor);
        label.setBorder(new EmptyBorder(20,20,20,20));
        setLayout(new BorderLayout());
        add(label,BorderLayout.SOUTH);
    }

    public BinaryTreeModel getModel() {
        return model;
    }
    @Override protected void paintComponent(Graphics g){
        super.paintComponent(g);
        drawTree(g,getWidth() / 2,30,model.getTree().getRoot(), getWidth() / 4);
    }
    public void clearTree(){
        model.clearTree();
    }
    private void drawTree(Graphics g, int x, int y, Node node, int offsetX){
        if(node == null) return;
        int childY = y + 50;
        if(node.left != null){
            int leftChildx = x - offsetX;
            g.drawLine(x,y,leftChildx,childY);
            drawTree(g,leftChildx,childY,node.left,offsetX/2);
        }
        if(node.right != null){
            int rightChildx = x + offsetX;
            g.drawLine(x,y,rightChildx,childY);
            drawTree(g,rightChildx,childY,node.right,offsetX/2);
        }
        if (node.visited) {
            g.setColor(AppSettings.visitedNodeColor);
        } else {
            g.setColor(AppSettings.nodeColor);
        }
        g.fillOval(x-13,y-13,26,26);
        g.setColor(AppSettings.fontColor);
        g.setFont(new Font("Monospaced", Font.PLAIN, 9));
        g.drawString(node.elem + "",x - 7,y + 3);
        g.drawString("height:" + node.height(),x - 25,y + 20);
        g.drawString("depth: " + node.depth(),x - 25,y + 30);
        g.setColor(AppSettings.edgeColor);
    }

    private String visitNode(Node node){
        node.visited = true;
        return node.elem + " ";
    }
    public void showBFS() {
        SwingWorker<Void, Node> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground(){
                StringBuilder str = new StringBuilder();
                str.append("Breadth-First-Search: ");
                Queue<Node> queue = new LinkedList<>();
                queue.add(model.getTree().getRoot());

                while (!queue.isEmpty()) {
                    Node curr = queue.remove();
                    str.append(visitNode(curr));

                    publish(curr);

                    if (curr.left != null) queue.add(curr.left);
                    if (curr.right != null) queue.add(curr.right);

                    label.setText(str.toString());

                    try {
                        Thread.sleep(500); // 0.5 seconds delay
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(500); // 0.5 seconds delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                model.getTree().setAllToNotVisited();
                repaint();
                return null;
            }

            @Override
            protected void process(java.util.List<Node> nodes) {
                repaint();
            }
        };

        worker.execute();
    }

    public void showDFS_Preorder() {
        SwingWorker<Void, Node> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                StringBuilder str = new StringBuilder();
                str.append("DFS Preorder: ");
                Stack<Node> stack = new Stack<>();
                stack.push(model.getTree().getRoot());

                while (!stack.isEmpty()) {
                    Node curr = stack.pop();
                    str.append(visitNode(curr));

                    publish(curr);

                    if (curr.right != null) stack.push(curr.right);
                    if (curr.left != null) stack.push(curr.left);

                    label.setText(str.toString());

                    try {
                        Thread.sleep(500); // 0.5 seconds delay
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(500); // 0.5 seconds delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                model.getTree().setAllToNotVisited();
                repaint();
                return null;
            }

            @Override
            protected void process(java.util.List<Node> nodes) {
                repaint();
            }
        };
        worker.execute();
    }

    public void showDFS_Postorder() {
        SwingWorker<Void, Node> worker = new SwingWorker<>() {
            private StringBuilder str = new StringBuilder();
            private void DFS_postorder(Node node){
                if(node.left != null) DFS_postorder(node.left);
                if(node.right != null) DFS_postorder(node.right);
                str.append(visitNode(node));
                label.setText(str.toString());
                repaint();
                try {
                    Thread.sleep(500); // 0.5 seconds delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            @Override
            protected Void doInBackground() {
                str.append("DFS Postorder: ");
                DFS_postorder(model.getTree().getRoot());
                try {
                    Thread.sleep(500); // 0.5 seconds delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                model.getTree().setAllToNotVisited();
                repaint();
                return null;
            }

            @Override
            protected void process(java.util.List<Node> nodes) {
                repaint();
            }
        };
        worker.execute();
    }
    public Void showDFS_Inorder(){
        SwingWorker<Void, Node> worker = new SwingWorker<>() {
            private StringBuilder str = new StringBuilder();
            private void DFS_inorder(Node node){
                if(node.left != null) DFS_inorder(node.left);
                str.append(visitNode(node));
                label.setText(str.toString());
                repaint();
                try {
                    Thread.sleep(500); // 0.5 seconds delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(node.right != null) DFS_inorder(node.right);
            }
            @Override
            protected Void doInBackground() {
                str.append("DFS Inorder: ");
                DFS_inorder(model.getTree().getRoot());
                try {
                    Thread.sleep(500); // 0.5 seconds delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                model.getTree().setAllToNotVisited();
                repaint();
                return null;
            }
            @Override
            protected void process(java.util.List<Node> nodes) {
                repaint();
            }
        };
        worker.execute();
        return null;
    }
}
