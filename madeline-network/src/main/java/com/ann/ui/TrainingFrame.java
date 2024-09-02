package com.ann.ui;

import java.awt.*;
import javax.swing.*;

import com.ann.madeline.MadelineNetwork;

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

        gridsPanel.add(column);

        mainPanel.add(gridsPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JButton addGridBt = new JButton("Add Grid");
        buttonPanel.add(addGridBt);
        addGridBt.addActionListener(e -> {
            String label = configPanel.getSelectedLabel();

            if (label == null || label.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Select a label", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            gridsListPanel.addGrid(new GridItem(listIndex++, label, grid.getGridData()));
        });

        JButton rmGridBt = new JButton("Remove Grid");
        buttonPanel.add(rmGridBt);
        rmGridBt.addActionListener(e -> {
            GridItem selectedGrid = gridsListPanel.getSelectedGrid();
            if (selectedGrid != null) {
                gridsListPanel.removeGrid(selectedGrid);
            }
        });

        JButton clrGridBt = new JButton("Clear Grid");
        buttonPanel.add(clrGridBt);
        clrGridBt.addActionListener(e -> grid.clear());

        JButton trainBt = new JButton("Train & Continue");
        buttonPanel.add(trainBt);
        trainBt.addActionListener(e -> train());

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public void train() {

        int n = grid.getGridSize() * grid.getGridSize();
        String[] labels = configPanel.getLabels();

        // GridItem[] gridItems = gridsListPanel.getGridItems();

        MadelineNetwork ann = new MadelineNetwork(
                n,
                labels,
                configPanel.getToleratedError(),
                configPanel.getLearningRate());

        System.out.println(ann);
    }

}
