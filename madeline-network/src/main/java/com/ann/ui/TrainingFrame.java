package com.ann.ui;

import java.awt.*;
import javax.swing.*;

public class TrainingFrame extends JFrame {

    private PixelGrid grid;
    private int listIndex = 0;

    public TrainingFrame(int gridSize) {
        setSize(650 + 200, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel.add(new ConfigurationPanel(), BorderLayout.WEST);

        JPanel gridsPanel = new JPanel();
        gridsPanel.setLayout(new BoxLayout(gridsPanel, BoxLayout.X_AXIS));

        grid = new PixelGrid(gridSize);
        gridsPanel.add(grid);

        JPanel column = new JPanel();
        column.setLayout(new BoxLayout(column, BoxLayout.Y_AXIS));
        column.setMaximumSize(new Dimension(150, 550));
        column.setPreferredSize(new Dimension(150, 550));

        column.add(new JLabel("SAVED GRIDS"), BorderLayout.CENTER);

        DefaultListModel<GridItem> listModel = new DefaultListModel<>();
        JList<GridItem> list = new JList<>(listModel);

        column.add(new JScrollPane(list));

        JButton removeButton = new JButton("Remove Grid");
        column.add(removeButton, BorderLayout.CENTER);

        removeButton.addActionListener(e -> {
            int selectedIndex = list.getSelectedIndex();
            if (selectedIndex != -1) {
                listModel.remove(selectedIndex); 
            }
        });

        gridsPanel.add(column);

        mainPanel.add(gridsPanel, BorderLayout.CENTER);

        JButton saveButton = new JButton("Save Grid");
        saveButton.addActionListener(e -> {

            String label = JOptionPane.showInputDialog(this, "Enter label for grid", "Save Grid", JOptionPane.PLAIN_MESSAGE);
            listModel.addElement(new GridItem(listIndex++, label, grid.getGridData()));

        });

        JButton clearButton = new JButton("Clear Grid");
        clearButton.addActionListener(e -> {
            grid.clear();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        buttonPanel.add(clearButton);
        buttonPanel.add(saveButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

}
