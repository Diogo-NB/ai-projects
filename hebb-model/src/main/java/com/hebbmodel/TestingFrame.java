package com.hebbmodel;

import java.awt.*;
import javax.swing.*;

public class TestingFrame extends JFrame {

    public TestingFrame(int[][] grid1Data, int[][] grid2Data) {
        setTitle("Test model");
        setSize(600, 600);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel gridsPanel = new JPanel();
        gridsPanel.setLayout(new GridLayout(1, 2, 10, 0));

        PixelGrid testGrid = new PixelGrid("Test grid");
        gridsPanel.add(testGrid);

        mainPanel.add(gridsPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Add padding above the buttons

        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        resultsPanel.setPreferredSize(new Dimension(100, 300));
        resultsPanel.setMinimumSize(new Dimension(100, 150));

        JButton testButton = new JButton("Test");
        testButton.addActionListener(e -> {
            // Creates an object that represents the Hebbian model
            HebbModel model = new HebbModel(100);

            /* ------------------------- Training process --------------------------- */

            int[][] inputs = new int[2][100]; // training input arrays

            // Converts the matrix representations into one-dimensional arrays
            inputs[0] = Util.flattenMatrix(grid1Data);
            inputs[1] = Util.flattenMatrix(grid2Data);

            int[] expectedOutputs = new int[] { 1, -1 }; // expected output values for each input

            // Trains the model with the input vectors and expected outputs
            model.train(inputs, expectedOutputs);

            /* ---------------------------- Testing ------------------------------ */

            int[][] testGridData = testGrid.getGridData();
            int[] testInput = Util.flattenMatrix(testGridData);

            // Add the results to the results panel
            float result = model.test(testInput);
            if (result > 0) {
                resultsPanel.add(new JLabel("Grid 1 (" + result + ")"));
            } else {
                resultsPanel.add(new JLabel("Grid 2 (" + result + ")"));
            }

            resultsPanel.revalidate();
            resultsPanel.repaint();
        });

        buttonPanel.add(testButton);
        resultsPanel.add(new JLabel("Results"));

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(resultsPanel, BorderLayout.EAST);

        add(mainPanel);
    }

}
