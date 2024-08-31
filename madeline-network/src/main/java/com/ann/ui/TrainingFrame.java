package com.ann.ui;

import java.awt.*;
import javax.swing.*;

import com.ann.models.*;

public class TrainingFrame extends JFrame {

    public TrainingFrame() {
        setSize(650, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel gridsPanel = new JPanel();
        gridsPanel.setLayout(new BoxLayout(gridsPanel, BoxLayout.X_AXIS));

        TextField editLabelField = new TextField("Grid");
        editLabelField.setPreferredSize(new Dimension(150, 550));
        PixelGrid grid = new PixelGrid(editLabelField, 15);
        gridsPanel.add(grid);

        JPanel column = new JPanel();
        column.setLayout(new BoxLayout(column, BoxLayout.Y_AXIS));
        column.setMaximumSize(new Dimension(150, 550));
        column.setPreferredSize(new Dimension(150, 550));
        column.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        gridsPanel.add(column);

        mainPanel.add(gridsPanel, BorderLayout.CENTER);


        JButton trainButton = new JButton("Train model & Continue");
        trainButton.addActionListener(e -> {

        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        buttonPanel.add(trainButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

}
