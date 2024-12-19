import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Random;

public class AppUI {
    private JFrame frame;
    private JPanel treePanel;
    private BinaryTreeController controller;

    public AppUI() {
        frame = new JFrame();
        frame.setTitle("App UI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setLayout(new BorderLayout());
        treePanel = new BinaryTreeGUI();
        controller = new BinaryTreeController();
        JPanel controlPanel = controller.getContentPanel();
        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.add(treePanel, BorderLayout.CENTER);
        setButtonListeners();

        treePanel.setBackground(AppSettings.backgroundColor);
    }
    private void setButtonListeners() {
        controller.getInsertButton().addActionListener(e -> {
            String input = controller.getValueTextField().getText();
            int num_input = Integer.parseInt(input);
            ((BinaryTreeGUI) treePanel).getModel().insert(num_input);
            treePanel.repaint();
            frame.repaint();
        });
        controller.getRemoveButton().addActionListener(e -> {
            String input = controller.getValueTextField().getText();
            int num_input = Integer.parseInt(input);
            ((BinaryTreeGUI) treePanel).getModel().delete(num_input);
            treePanel.repaint();
            frame.repaint();
        });
        controller.getAdd5RandomValuesButton().addActionListener(e -> {
            int ctr = 0;
            while(ctr < 5) {

                int rand = new Random().nextInt(0,101);

                if(!((BinaryTreeGUI)treePanel).getModel().search_value(rand)) {
                    System.out.println(ctr);
                    ctr++;
                    ((BinaryTreeGUI)treePanel).getModel().insert(rand);
                }
            }
            treePanel.repaint();
            frame.repaint();
        });
        controller.getShowBreadthFirstSearchButton().addActionListener(e -> {
            ((BinaryTreeGUI)treePanel).showBFS();
        });
        controller.getPreorderDFSButton().addActionListener(e -> {
            ((BinaryTreeGUI)treePanel).showDFS_Preorder();
        });
        controller.getPostorderDFSButton().addActionListener(e -> {
            ((BinaryTreeGUI)treePanel).showDFS_Postorder();
        });
        controller.getInorderDFSButton().addActionListener(e -> {
            ((BinaryTreeGUI)treePanel).showDFS_Inorder();
        });
        controller.getClearTreeButton().addActionListener(e -> {
            ((BinaryTreeGUI)treePanel).clearTree();
            treePanel.repaint();
            System.gc();
        });
    }
    public void show(){
        frame.setVisible(true);
    }
}
