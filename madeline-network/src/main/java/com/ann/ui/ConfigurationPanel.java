package com.ann.ui;

import java.awt.*;
import javax.swing.*;

public class ConfigurationPanel extends JPanel {

    private GridBagConstraints gbc;
    private LabeledComponent gridSizeField;
    private LabeledComponent learningRateField;
    private LabeledComponent classesTypeField;

    @Override
    public Component add(Component comp) {
        super.add(comp, gbc);
        gbc.gridy++;
        return comp;
    }

    public float getToleratedError() {
        return (float) gridSizeField.getValue();
    }

    public float getLearningRate() {
        return (float) learningRateField.getValue();
    }

    public String getClassesType() {
        return (String) classesTypeField.getValue();
    }

    public ConfigurationPanel() {
        setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel title = new JLabel("Configuration");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title);

        SpinnerNumberModel toleratedErrorSpinnerModel = new SpinnerNumberModel(0.005, 0.0, 1, 0.005);
        JSpinner toleratedErrorSpinner = new JSpinner(toleratedErrorSpinnerModel);
        toleratedErrorSpinner.setPreferredSize(new Dimension(60, 20));
        gridSizeField = new LabeledComponent("Tolerated error:", toleratedErrorSpinner) {

            @Override
            public Object getValue() {
                return Float.parseFloat(toleratedErrorSpinner.getValue().toString());
            }

        };

        add(gridSizeField);

        SpinnerNumberModel learningRateSpinnerModel = new SpinnerNumberModel(0.06, 0.0, 1, 0.005);
        JSpinner learningRateSpinner = new JSpinner(learningRateSpinnerModel);
        learningRateSpinner.setPreferredSize(new Dimension(60, 20));
        learningRateField = new LabeledComponent("Model's Learning rate:", learningRateSpinner) {

            @Override
            public Object getValue() {
                return Float.parseFloat(learningRateSpinner.getValue().toString());
            }

        };
        add(learningRateField);

        String[] options = { "OBV", "One of Classes" };
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setPreferredSize(new Dimension(120, 20));
        classesTypeField = new LabeledComponent("Classes: ", comboBox) {

            @Override
            public Object getValue() {
                return comboBox.getSelectedItem();
            }

        };

        add(classesTypeField);
    }

}
