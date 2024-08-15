package com.hebbmodel;

import java.awt.*;
import javax.swing.*;

public class TrainingFrame extends JFrame {

    public TrainingFrame() {
        setSize(1000, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel gridsPanel = new JPanel();
        gridsPanel.setLayout(new GridLayout(1, 2, 10, 0));

        PixelGrid grid1 = new PixelGrid("Grid #1");
        gridsPanel.add(grid1);

        PixelGrid grid2 = new PixelGrid("Grid #2");
        gridsPanel.add(grid2);

        mainPanel.add(gridsPanel, BorderLayout.CENTER);

        JButton trainButton = new JButton("Train model & Continue");
        trainButton.addActionListener(e -> {
            // Gets the data representations of both grids
            int[][] grid1Data = grid1.getGridData();
            int[][] grid2Data = grid2.getGridData();

            // Creates an object that represents the Hebbian model
            HebbModel model = new HebbModel(100);

            int[][] inputs = new int[2][100]; // training input arrays

            // Converts the matrix representations into one-dimensional arrays
            inputs[0] = Util.flattenMatrix(grid1Data);
            inputs[1] = Util.flattenMatrix(grid2Data);

            int[] expectedOutputs = new int[] { 1, -1 }; // expected output values for each input

            // Trains the model with the input vectors and expected outputs
            model.train(inputs, expectedOutputs);

            // Open the frame for testing the model
            JFrame testingFrame = new TestingFrame(model);
            testingFrame.setVisible(true);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        buttonPanel.add(trainButton);

        HebbModel[] models = new HebbModel[] { new HebbModel(10), new HebbModel(10) };
        JComboBox<HebbModel> comboBox = new JComboBox<>(models);
        buttonPanel.add(comboBox);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

}
