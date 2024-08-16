package com.ann.ui;

import java.awt.*;
import javax.swing.*;

import com.ann.models.*;
import com.ann.util.CollectionsUtil;

public class TrainingFrame extends JFrame {

    public TrainingFrame() {
        setSize(1000, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel gridsPanel = new JPanel();
        gridsPanel.setLayout(new GridLayout(1, 2, 10, 0));

        EditLabelField editLabelField1 = new EditLabelField("Grid #1");
        PixelGrid grid1 = new PixelGrid(editLabelField1);
        gridsPanel.add(grid1);

        EditLabelField editLabelField2 = new EditLabelField("Grid #2");
        PixelGrid grid2 = new PixelGrid(editLabelField2);
        gridsPanel.add(grid2);

        mainPanel.add(gridsPanel, BorderLayout.CENTER);

        ANNModel[] models = new ANNModel[] { new PerceptronModel(100), new HebbModel(100) };
        JComboBox<ANNModel> comboBox = new JComboBox<>(models);
        comboBox.setSelectedIndex(0);

        JButton trainButton = new JButton("Train model & Continue");
        trainButton.addActionListener(e -> {
            int[][] grid1Data = grid1.getGridData();
            int[][] grid2Data = grid2.getGridData();

            int[][] inputs = new int[2][100]; // training input arrays

            // Converts the matrix representations into one-dimensional arrays
            inputs[0] = CollectionsUtil.flatten(grid1Data);
            inputs[1] = CollectionsUtil.flatten(grid2Data);

            int[] expectedOutputs = new int[] { 1, -1 }; // expected output values for each input

            // Get the selected model
            ANNModel model = (ANNModel) comboBox.getSelectedItem();
            model.reset();

            model.train(inputs, expectedOutputs);

            // Open the frame for testing the model
            String grid1Label = editLabelField1.getLabel();
            String grid2Label = editLabelField2.getLabel();

            JFrame testingFrame = new TestingFrame(model, grid1Label, grid2Label);
            testingFrame.setTitle("Testing " + model.toString());
            testingFrame.setVisible(true);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        buttonPanel.add(trainButton);

        buttonPanel.add(comboBox);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

}
