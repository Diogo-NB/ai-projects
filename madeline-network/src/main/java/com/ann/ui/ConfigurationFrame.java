package com.ann.ui;

import java.awt.*;
import javax.swing.*;

public class ConfigurationFrame extends JFrame {

    GridBagConstraints gbc;
    JPanel panel;

    @Override
    public Component add(Component comp) {
        panel.add(comp, gbc);
        gbc.gridy++;
        return comp;
    }

    public ConfigurationFrame() {
        super();
        setResizable(false);
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 0, 10, 0);

        // Adding a title
        JLabel title = new JLabel("Configuration");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title);

        JSpinner gridSizeSpinner = new JSpinner(new SpinnerNumberModel(10, 2, 30, 1));
        gridSizeSpinner.setPreferredSize(new Dimension(35, 20));
        LabeledComponent gridSizeField = new LabeledComponent("Input Grid Size:", gridSizeSpinner) {

            @Override
            public Object getValue() {
                return Integer.parseInt(gridSizeSpinner.getValue().toString());
            }

        };

        add(gridSizeField);

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

        add(learningRateField);

        // gbc.gridy++;
        // JButton btn3 = new JButton("Next");
        // btn3.addActionListener(e -> {
        //     TrainingFrame ConfigurationFrameFrame = new TrainingFrame();
        //     ConfigurationFrameFrame.setTitle("Madeline Network");
        //     ConfigurationFrameFrame.setVisible(true);
        // });
        // add(btn3);

        super.add(panel);
    }

}
