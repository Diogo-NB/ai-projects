package com.hebbmodel;

import java.awt.*;
import javax.swing.*;

public class TestingFrame extends JFrame {

    private HebbModel model;
    private PixelGrid testGrid;
    private JPanel resultsPanel;
    private int resultIndex = 0;

    public TestingFrame(HebbModel model) {
        this.model = model;
        setTitle("Test model");
        setSize(620, 600);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel gridsPanel = new JPanel();
        gridsPanel.setLayout(new GridLayout(1, 2, 10, 0));
        testGrid = new PixelGrid("Test grid");
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
        int[][] testGridData = testGrid.getGridData();
        int[] testInput = CollectionsUtil.flatten(testGridData);

        // Add the results to the results panel
        float result = model.test(testInput);
        if (result > 0) {
            resultsPanel.add(new JLabel("#" + resultIndex++ + " Grid 1 (" + result + ")"));
        } else {
            resultsPanel.add(new JLabel("#" + resultIndex++ + " Grid 2 (" + result + ")"));
        }

        resultsPanel.revalidate();
        resultsPanel.repaint();
    }
}
