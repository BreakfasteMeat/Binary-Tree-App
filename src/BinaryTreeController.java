import javax.swing.*;

public class BinaryTreeController {
    private JPanel ContentPanel;
    private JTextField valueTextField;
    private JButton insertButton;
    private JButton removeButton;
    private JButton add5RandomValuesButton;
    private JButton showBreadthFirstSearchButton;
    private JButton preorderDFSButton;
    private JButton postorderDFSButton;
    private JButton inorderDFSButton;
    private JButton clearTreeButton;
    private JRadioButton avlTreeButton;

    public JPanel getContentPanel() {
        return ContentPanel;
    }

    public JButton getClearTreeButton() {
        return clearTreeButton;
    }



    public JTextField getValueTextField() {
        return valueTextField;
    }

    public JButton getInsertButton() {
        return insertButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public JButton getAdd5RandomValuesButton() {
        return add5RandomValuesButton;
    }

    public JButton getShowBreadthFirstSearchButton() {
        return showBreadthFirstSearchButton;
    }

    public JButton getPreorderDFSButton() {
        return preorderDFSButton;
    }

    public JButton getPostorderDFSButton() {
        return postorderDFSButton;
    }

    public JButton getInorderDFSButton() {
        return inorderDFSButton;
    }

    public JRadioButton getAvlTreeButton() {
        return avlTreeButton;
    }
}
