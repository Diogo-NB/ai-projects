package com.hebbmodel;

import java.awt.*;
import javax.swing.*;

public class Main extends JFrame {

    public Main() {
        setTitle("Hebb Model Implementation");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a main panel with BorderLayout to hold the PixelGrids and the button
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10)); // Add padding
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add empty border for spacing

        // Create a panel to hold the two PixelGrids side by side
        JPanel gridsPanel = new JPanel();
        gridsPanel.setLayout(new GridLayout(1, 2, 10, 0)); // Add horizontal gap

        // Create two PixelGrid instances
        PixelGrid grid1 = new PixelGrid();
        PixelGrid grid2 = new PixelGrid();

        // Add the PixelGrid instances to the grids panel
        gridsPanel.add(grid1);
        gridsPanel.add(grid2);

        // Add the grids panel to the main panel
        mainPanel.add(gridsPanel, BorderLayout.CENTER);

        // Create a submit button for training neural network
        JButton trainButton = new JButton("Train Neural Network");
        trainButton.addActionListener(e -> {
            int[][] grid1Representation = grid1.getGridRepresentation();
            int[][] grid2Representation = grid2.getGridRepresentation();

            int modelSize = grid1Representation.length * grid1Representation.length;

            // HeebsRule.train(grid1Representation, grid2Representation, 1, -1, this);
            HebbModel model = new HebbModel(modelSize);

            int[][] inputs = new int[2][modelSize];
            inputs[0] = Util.flattenMatrix(grid1Representation);
            inputs[1] = Util.flattenMatrix(grid2Representation);

            int[] expectedOutputs = new int[2];
            expectedOutputs[0] = 1;
            expectedOutputs[1] = -1;

            model.train(inputs, expectedOutputs);
            System.out.println(model);
            model.test(inputs[0]);
            model.test(inputs[1]);
        });

        // Create a panel to hold the buttons with some padding
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Add padding above the buttons
        buttonPanel.add(trainButton);

        // Add the button panel to the main panel
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main main = new Main();
            main.setVisible(true);
        });
    }
}
