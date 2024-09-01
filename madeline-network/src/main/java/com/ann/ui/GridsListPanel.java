package com.ann.ui;

import java.awt.*;
import javax.swing.*;

public class GridsListPanel extends JPanel {

    private GridBagConstraints gbc;
    private DefaultListModel<GridItem> gridsModel;
    private JList<GridItem> grids;

    @Override
    public Component add(Component comp) {
        super.add(comp, gbc);
        gbc.gridy++;
        return comp;
    }

    public void addGrid(GridItem gridItem) {
        gridsModel.addElement(gridItem);
    }

    public void removeGrid(GridItem gridItem) {
        gridsModel.removeElement(gridItem);
    }

    public GridItem getSelectedGrid() {
        return grids.getSelectedValue();
    }

    public GridsListPanel() {
        setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel title = new JLabel("Grids", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title);

        gridsModel = new DefaultListModel<>();
        grids = new JList<>(gridsModel);

        JScrollPane scrollPane = new JScrollPane(grids);
        scrollPane.setPreferredSize(new Dimension(100, 350));

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH; // Expand to fill the available space

        add(scrollPane);

        gbc.fill = GridBagConstraints.HORIZONTAL;
    }

}
