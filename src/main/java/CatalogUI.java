import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CatalogUI extends JFrame {
    private JPanel mainPanel;
    private JList catalogList;
    private JScrollPane scroll;
    private JButton done;
    private JTextArea search;
    private Catalog catalog;
    private JFrame frame;
    private User user;

    public CatalogUI(User user, String option, String query) {
        try {
            this.user = user;
            run(option, query);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Object[] convert(Item[] toConvert)
    {
        Object[] output = new Object[toConvert.length];

        for (int i = 0; i < toConvert.length; i++)
        {
            output[i] = new ButtonItem(toConvert[i]);
        }

        return output;
    }

    public void run(String option, String query) {
        try {

            catalog = new Catalog();
            catalog.getCatalog();
            Object[] items;
            if (option.equals("sort")) {
                items = convert(catalog.sorted(query));
            } else if (option.equals("search")) {
                items = convert(catalog.searchResult(query));
            } else {
                items = new ButtonItem[catalog.getItems().size()];
                for (int i = 0; i < items.length; i++) {
                    items[i] = new ButtonItem(catalog.getItems().get(i));
                }
            }

            Object[] list = new Object[items.length + 1];
            list[0] = new ButtonItem("Options");

            for (int i = 0; i < items.length; i++)
            {
                list[i + 1] = items[i];
            }

            catalogList = new JList(list);
            catalogList.setCellRenderer(new ButtonListRenderer());
            catalogList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            catalogList.setVisibleRowCount(5);
            catalogList.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    clickButtonAt(e.getPoint());
                }
            });

            done.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    Confirm confirm = new Confirm(user, catalog);
                }
            });

            frame = new JFrame();
            scroll = new JScrollPane(catalogList);
            //mainPanel.add(scroll, BorderLayout.SOUTH);
            frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            frame.getContentPane().add(scroll);
            //frame.getContentPane().add(mainPanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void clickButtonAt(Point point) {
        int index = catalogList.locationToIndex(point);
        ButtonItem item = (ButtonItem) catalogList.getModel().getElementAt(index);
        item.getButton().doClick();
    }

    public void close() {
        frame.setVisible(false);
        frame.dispose();
    }

    public class ButtonItem {
        private JButton button;

        public ButtonItem(Item x) {
            String name = x.getName();
            this.button = new JButton(name);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    close();
                    System.out.println(button.getText() + " clicked.");
                    ItemUI item = new ItemUI(x, user, catalog);
                }
            });
        }

        public ButtonItem(String options) {
            this.button = new JButton(options);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    close();
                    System.out.println(button.getText() + " clicked.");
                    Options options = new Options(user, catalog);
                }
            });
        }

        public JButton getButton() {
            return button;
        }

        public String toString() {
            return button.getText();
        }
    }

    public class ButtonListRenderer extends JButton implements ListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList comp, Object value, int index, boolean isSelected, boolean hasFocus) {
            setEnabled(comp.isEnabled());
            setFont(comp.getFont());
            setText(value.toString());

            if (isSelected) {
                setBackground(comp.getSelectionBackground());
                setForeground(comp.getSelectionForeground());
            } else {
                setBackground(comp.getBackground());
                setForeground(comp.getForeground());
            }
            return this;
        }
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        done = new JButton();
        done.setText("Confirm Order");
        mainPanel.add(done, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        search = new JTextArea();
        mainPanel.add(search, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        scroll = new JScrollPane();
        mainPanel.add(scroll, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        catalogList = new JList();
        scroll.setViewportView(catalogList);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
