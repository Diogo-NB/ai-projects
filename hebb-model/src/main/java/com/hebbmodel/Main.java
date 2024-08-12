package com.hebbmodel;

import java.awt.*;
import javax.swing.*;

public class Main extends JFrame {

    public Main() {
        setTitle("Hebb Model Implementation");
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

        JButton trainButton = new JButton("Next");
        trainButton.addActionListener(e -> {

            // Gets the data representations of both grids
            int[][] grid1Data = grid1.getGridData();
            int[][] grid2Data = grid2.getGridData();

            JFrame testingFrame = new TestingFrame(grid1Data, grid2Data);
            testingFrame.setVisible(true);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Add padding above the buttons
        buttonPanel.add(trainButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main main = new Main();
            main.setVisible(true);
        });
    }
}
