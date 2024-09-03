package com.ann.ui;

import java.awt.*;
import javax.swing.*;

import com.ann.madeline.MadelineNetwork;
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

        int inputSize = grid.getGridSize() * grid.getGridSize();
        // String[] labels = configPanel.getLabels();

        GridItem[] gridItems = gridsListPanel.getGridItems();

        Vector[] inputs = new Vector[gridItems.length];

        Vector[] outputs = new Vector[gridItems.length]; // Vetor one of classes

        String[] labels = new String[inputs.length];

        // Montando entradas e saídas
        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = gridItems[i].getGridData();
            labels[i] = gridItems[i].getLabel();

            outputs[i] = Vector.zeros(inputs.length);
            outputs[i].set(i, 1);

            // System.out.println("Outputs[" + i + "] = " + outputs[i]);
            System.out.println("inputs[" + i + "] = " + inputs[i]);
        }

        MadelineNetwork model = new MadelineNetwork(
                inputSize,
                labels.length,
                configPanel.getToleratedError(),
                configPanel.getLearningRate());

        System.out.println(model);

        model.train(inputs, outputs);

        TestingFrame testingFrame = new TestingFrame(grid.getGridSize(), model, labels, outputs);
        testingFrame.setVisible(true);

        // Vector y = model.test(inputs[0]);
        // System.out.println(y);
        // float minDistanceFound = Float.MAX_VALUE; // Menor distância encontrada
        // String label = "NOT FOUND";

        // // Para cada output, calcula a distância entre output e y
        // for (int i = 0; i < outputs.length; i++) {
        // Vector t = outputs[i];

        // Vector d = Vector.subtract(t, y);
        // d.multiply(d);
        // float distance = (float) Math.sqrt(d.sum());
        // if (distance < minDistanceFound) {
        // minDistanceFound = distance;
        // label = labels[i];
        // }
        // }

        // System.out.println("Found " + label);

    }

}
