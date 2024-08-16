package com.ann.ui;

import java.awt.*;
import javax.swing.*;

import com.ann.models.ANNModel;
import com.ann.util.CollectionsUtil;

public class TestingFrame extends JFrame {

    private ANNModel model;
    private PixelGrid testGrid;
    private JPanel resultsPanel;
    private int resultIndex = 0;
    private String grid1Label;
    private String grid2Label;

    /**
     * @model the model to be tested
     */
    public TestingFrame(ANNModel model) {
        this(model, "Grid #1", "Grid #2");
    }

    /**
     * @model the model to be tested
     * @grid1Label the label for the first grid
     * @grid2Label the label for the second grid
     */
    public TestingFrame(ANNModel model, String grid1Label, String grid2Label) {
        this.grid1Label = grid1Label;
        this.grid2Label = grid2Label;
        this.model = model;
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
            resultsPanel.add(new JLabel("#" + resultIndex++ + " " + grid1Label));
        } else {
            resultsPanel.add(new JLabel("#" + resultIndex++ + " " + grid2Label));
        }

        resultsPanel.revalidate();
        resultsPanel.repaint();
    }
}
