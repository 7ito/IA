import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Confirm {
    private JPanel mainPanel;
    private JLabel title;
    private JButton confirm;
    private JTextField date;
    private JLabel key;
    private JTable order;
    private JLabel error;
    private Catalog catalog;
    private User user;

    public Confirm(User user, Catalog catalog) {
        this.user = user;
        this.catalog = catalog;

        $$$setupUI$$$();
        convert(user.getOrder());
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 600, 600);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setVisible(true);

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (date.getText().equals("")) {
                        error.setText("You must enter a return date");
                    } else {
                        user.confirmOrder(catalog, date.getText());
                        frame.setVisible(false);
                        frame.dispose();
                        Name name = new Name();
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }


    public static void main(String[] args) throws Exception {
        Catalog catalog = new Catalog();
        catalog.getCatalog();
        User user = new User("Test");
        user.addToOrder(catalog.getItems().get(1), 32);
        new Confirm(user, catalog);
    }

    public String[][] convert(ArrayList<Item> userOrder) {
        String[][] orderData = new String[userOrder.size()][2];

        for (int i = 0; i < userOrder.size(); i++) {
            orderData[i][0] = userOrder.get(i).getName();
            orderData[i][1] = "" + userOrder.get(i).getAmount();
        }
        return orderData;
    }


    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        mainPanel = new JPanel();
        mainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 8, new Insets(0, 0, 0, 0), -1, -1));
        title = new JLabel();
        title.setText("Confirm Order");
        mainPanel.add(title, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 8, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        key = new JLabel();
        key.setText("Return Date: mm/dd/yy");
        mainPanel.add(key, new com.intellij.uiDesigner.core.GridConstraints(2, 7, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        confirm = new JButton();
        confirm.setText("Confirm");
        mainPanel.add(confirm, new com.intellij.uiDesigner.core.GridConstraints(3, 6, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        date = new JTextField();
        mainPanel.add(date, new com.intellij.uiDesigner.core.GridConstraints(3, 7, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        mainPanel.add(order, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 8, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        error = new JLabel();
        error.setText("");
        mainPanel.add(error, new com.intellij.uiDesigner.core.GridConstraints(2, 6, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        String[] columnNames = {"Item", "Amount"};
        order = new JTable(convert(user.getOrder()), columnNames);
    }
}
