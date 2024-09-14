package com.ann.ui;

import java.awt.*;
import javax.swing.*;

import com.ann.madeline.AnnClasses;
import com.ann.madeline.MadelineNetwork;
import com.ann.util.Vector;

public class TestingFrame extends JFrame {

    private MadelineNetwork model;
    private PixelGrid testGrid;
    private JPanel resultsPanel;
    private int resultIndex = 0;
    private AnnClasses<String> classes;

    public TestingFrame(int gridSize, MadelineNetwork model, AnnClasses<String> classes) {
        this.classes = classes;
        this.model = model;

        setSize(620, 600);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel gridsPanel = new JPanel();
        gridsPanel.setLayout(new GridLayout(1, 2, 10, 0));
        testGrid = new PixelGrid(gridSize);
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

        JButton clrGridBt = new JButton("Clear Grid");
        buttonPanel.add(clrGridBt);
        clrGridBt.addActionListener(e -> testGrid.clear());

        mainPanel.add(gridsPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(resultsPanel, BorderLayout.EAST);

        add(mainPanel);
    }

    public void testModelAction() {
        Vector testInput = testGrid.getGridData();
        Vector y = model.test(testInput);
        String label = classes.getClass(y);

        resultsPanel.add(new JLabel("#" + resultIndex++ + " " + label));

        resultsPanel.revalidate();
        resultsPanel.repaint();
    }
}
