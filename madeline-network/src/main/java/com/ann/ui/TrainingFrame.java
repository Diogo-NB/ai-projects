package com.ann.ui;

import java.awt.*;
import javax.swing.*;

public class TrainingFrame extends JFrame {

    private PixelGrid grid;
    private ConfigurationPanel configPanel;
    private GridsListPanel gridsListPanel;
    private int listIndex = 1;

    public TrainingFrame(int gridSize) {
        setSize(650 + 200, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        configPanel = new ConfigurationPanel();
        mainPanel.add(configPanel, BorderLayout.WEST);

        JPanel gridsPanel = new JPanel();
        gridsPanel.setLayout(new BoxLayout(gridsPanel, BoxLayout.X_AXIS));

        grid = new PixelGrid(gridSize);
        gridsPanel.add(grid);

        JPanel column = new JPanel();
        column.setLayout(new BoxLayout(column, BoxLayout.Y_AXIS));
        column.setMaximumSize(new Dimension(125, 550));
        column.setPreferredSize(new Dimension(125, 550));

        gridsListPanel = new GridsListPanel();
        column.add(gridsListPanel);

        DefaultListModel<GridItem> listModel = new DefaultListModel<>();
        JList<GridItem> list = new JList<>(listModel);
        JScrollPane listScrollPane = new JScrollPane(list);
        listScrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        // column.add(listScrollPane);

        JButton removeButton = new JButton("Remove Grid");
        // column.add(removeButton, BorderLayout.CENTER);

        removeButton.addActionListener(e -> {
            int selectedIndex = list.getSelectedIndex();
            if (selectedIndex != -1) {
                listModel.remove(selectedIndex);
            }
        });


        gridsPanel.add(column);

        mainPanel.add(gridsPanel, BorderLayout.CENTER);

        JButton addButton = new JButton("Add Grid");
        gridsListPanel.add(addButton);
        addButton.addActionListener(e -> {
            String label = configPanel.getSelectedLabel();

            if (label == null || label.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Select a label", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            listModel.addElement(new GridItem(listIndex++, label, grid.getGridData()));
            grid.clear();
        });

        JButton clearButton = new JButton("Clear Grid");
        clearButton.addActionListener(e -> grid.clear());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        buttonPanel.add(clearButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

}
