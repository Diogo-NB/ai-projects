package com.ann.ui;

import java.awt.*;
import javax.swing.*;

import java.util.*;

import com.ann.madeline.AnnClasses;
import com.ann.madeline.MadelineNetwork;
import com.ann.madeline.ObvClasses;
import com.ann.util.Vector;

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
            // String label = configPanel.getSelectedLabel();

            String label = JOptionPane.showInputDialog(this, "Enter a label:", "Label Input",
                    JOptionPane.INFORMATION_MESSAGE);

            if (label == null || label.isEmpty()) {
                // JOptionPane.showMessageDialog(this, "Select a label", "Error",
                // JOptionPane.ERROR_MESSAGE);
                return;
            }

            gridsListPanel.addGrid(new GridItem(listIndex++, label.toUpperCase(), grid.getGridData()));
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

        int inputSize = grid.getGridSize() * grid.getGridSize();

        GridItem[] gridItems = gridsListPanel.getGridItems();

        Vector[] inputs = new Vector[gridItems.length];

        Set<String> labels = new HashSet<>();

        // Montando entradas e saídas
        for (int i = 0; i < inputs.length; i++) {
            labels.add(gridItems[i].getLabel().toUpperCase());
            inputs[i] = gridItems[i].getGridData();

            System.out.println("inputs[" + i + "] = " + inputs[i]);
        }

        AnnClasses<String> annClasses;

        if (configPanel.getClassesType().equals("ObvClasses")) {
            annClasses = new ObvClasses<>(labels.toArray(new String[0]));
        } else {
            annClasses = new AnnClasses<>(labels.toArray(new String[0]));
        }

        Vector[] outputs = new Vector[inputs.length];

        for (int i = 0; i < outputs.length; i++) {
            String inputLabel = gridItems[i].getLabel().toUpperCase();
            Vector inputTarget = annClasses.getClassArray(inputLabel);
            outputs[i] = inputTarget;
        }

        int outputSize = outputs[0].size();
        MadelineNetwork model = new MadelineNetwork(
                inputSize,
                outputSize,
                configPanel.getToleratedError(),
                configPanel.getLearningRate());

        model.train(inputs, outputs);

        TestingFrame testingFrame = new TestingFrame(grid.getGridSize(), model, annClasses);
        testingFrame.setVisible(true);
    }

}
