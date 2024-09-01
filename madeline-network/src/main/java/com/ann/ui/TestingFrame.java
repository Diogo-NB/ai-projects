package com.ann.ui;

import java.awt.*;
import javax.swing.*;

import com.ann.madeline.MadelineNetwork;

public class TestingFrame extends JFrame {

    private MadelineNetwork model;
    private PixelGrid testGrid;
    private JPanel resultsPanel;
    private int resultIndex = 0;
    private String grid1Label;
    private String grid2Label;

    public TestingFrame(MadelineNetwork model) {
        this(model, "Grid #1", "Grid #2");
    }

    public TestingFrame(MadelineNetwork model, String grid1Label, String grid2Label) {
        this.grid1Label = grid1Label;
        this.grid2Label = grid2Label;
        this.model = model;
        setSize(620, 600);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel gridsPanel = new JPanel();
        gridsPanel.setLayout(new GridLayout(1, 2, 10, 0));
        testGrid = new PixelGrid(15);
        gridsPanel.add(testGrid);

        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        resultsPanel.setPreferredSize(new Dimension(120, 300));
        resultsPanel.setMinimumSize(new Dimension(120, 150));
        resultsPanel.add(new JLabel("Results"));

        JButton testButton = new JButton("Test model");
        testButton.addActionListener(e -> {
            testModelAction();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        buttonPanel.add(testButton);

        mainPanel.add(gridsPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(resultsPanel, BorderLayout.EAST);

        add(mainPanel);
    }

    public void testModelAction() {
        // int[][] testGridData = testGrid.getGridData();
        // int[] testInput = CollectionsUtil.flatten(testGridData);

        // // Add the results to the results panel
        // float result = model.test(testInput);
        // if (result >= 0.0f) {
        //     resultsPanel.add(new JLabel("#" + resultIndex++ + " " + grid1Label));
        // } else {
        //     resultsPanel.add(new JLabel("#" + resultIndex++ + " " + grid2Label));
        // }

        // resultsPanel.revalidate();
        // resultsPanel.repaint();
    }
}
