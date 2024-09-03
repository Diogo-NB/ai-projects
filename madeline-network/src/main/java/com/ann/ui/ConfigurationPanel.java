package com.ann.ui;

import java.awt.*;
import javax.swing.*;

public class ConfigurationPanel extends JPanel {

    private GridBagConstraints gbc;
    private LabeledComponent gridSizeField;
    private LabeledComponent learningRateField;
    private DefaultListModel<String> labelsModel;
    private JList<String> labels;

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

    public String getSelectedLabel() {
        return labels.getSelectedValue();
    }

    public String[] getLabels() {
        labelsModel.toArray();
        String[] labels = new String[labelsModel.size()];
        for (int i = 0; i < labelsModel.size(); i++) {
            labels[i] = labelsModel.get(i);
        }
        return labels;
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

        labelsModel = new DefaultListModel<>();
        labels = new JList<>(labelsModel);
        labels.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        SpinnerNumberModel toleratedErrorSpinnerModel = new SpinnerNumberModel(0.01, 0.0, 1, 0.005);
        JSpinner toleratedErrorSpinner = new JSpinner(toleratedErrorSpinnerModel);
        toleratedErrorSpinner.setPreferredSize(new Dimension(60, 20));
        gridSizeField = new LabeledComponent("Tolerated error:", toleratedErrorSpinner) {

            @Override
            public Object getValue() {
                return Float.parseFloat(toleratedErrorSpinner.getValue().toString());
            }

        };

        add(gridSizeField);

        SpinnerNumberModel learningRateSpinnerModel = new SpinnerNumberModel(0.1, 0.0, 1, 0.005);
        JSpinner learningRateSpinner = new JSpinner(learningRateSpinnerModel);
        learningRateSpinner.setPreferredSize(new Dimension(60, 20));
        learningRateField = new LabeledComponent("Model's Learning rate:", learningRateSpinner) {

            @Override
            public Object getValue() {
                return Float.parseFloat(learningRateSpinner.getValue().toString());
            }

        };
        add(learningRateField);

        JScrollPane scrollPane = new JScrollPane(labels);
        scrollPane.setPreferredSize(new Dimension(100, 200));

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.VERTICAL;

        add(new JLabel("Labels"));

        add(scrollPane);

        JButton addLabelBt = new JButton("Add Label");
        addLabelBt.addActionListener(e -> {
            String label = JOptionPane.showInputDialog(this, "Enter label", "Add label", JOptionPane.PLAIN_MESSAGE);
            label = label.trim();

            if (label == null || label.isEmpty() || labelsModel.contains(label)) {
                return;
            }

            labelsModel.addElement(label);
            labels.setSelectedValue(label, true);
        });

        add(addLabelBt);
    }

}
