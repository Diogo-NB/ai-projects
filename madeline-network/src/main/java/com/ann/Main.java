package com.ann;

import java.awt.*;
import javax.swing.*;

import com.ann.ui.*;

public class Main extends JFrame {

    public Main() {
        super();
        setResizable(false);
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 0, 10, 0);

        // Adding a title
        JLabel title = new JLabel("Configuration");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(title, gbc);

        gbc.gridy++;
        JSpinner gridSizeSpinner = new JSpinner(new SpinnerNumberModel(10, 2, 30, 1));
        gridSizeSpinner.setPreferredSize(new Dimension(35, 20));
        LabeledComponent gridSizeField = new LabeledComponent("Input Grid Size:", gridSizeSpinner) {

            @Override
            public Object getValue() {
                return Integer.parseInt(gridSizeSpinner.getValue().toString());
            }

        };

        panel.add(gridSizeField, gbc);

        gbc.gridy++;
        SpinnerNumberModel model = new SpinnerNumberModel(0.1, 0.0, 1, 0.05);
        JSpinner learningRateSpinner = new JSpinner(model);
        learningRateSpinner.setPreferredSize(new Dimension(50, 20));
        LabeledComponent learningRateField = new LabeledComponent("Model's Learning rate:", learningRateSpinner) {

            @Override
            public Object getValue() {
                return Float.parseFloat(learningRateSpinner.getValue().toString());
            }

        };

        panel.add(learningRateField, gbc);

        gbc.gridy++;
        JButton btn3 = new JButton("Next");
        btn3.addActionListener(e -> {
            TrainingFrame mainFrame = new TrainingFrame();
            mainFrame.setTitle("Madeline Network");
            mainFrame.setVisible(true);
        });
        panel.add(btn3, gbc);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main mainFrame = new Main();
            mainFrame.setTitle("Madeline Network");
            mainFrame.setVisible(true);

            // TrainingFrame mainFrame = new TrainingFrame();
            // mainFrame.setTitle("Madeline Network");
            // mainFrame.setVisible(true);
        });
    }
}
