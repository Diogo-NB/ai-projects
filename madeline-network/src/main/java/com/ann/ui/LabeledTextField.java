package com.ann.ui;

import javax.swing.*;
import java.awt.*;

public class LabeledTextField extends JPanel {

    private JLabel label;
    private JTextField textField;

    public LabeledTextField(String labelText, String textFieldText) {
        label = new JLabel(labelText);
        textField = new JTextField(textFieldText);

        textField.setHorizontalAlignment(JTextField.CENTER);

        setLayout(new FlowLayout(FlowLayout.LEFT, 6, 0));

        add(label);
        add(textField);
    }

    public String getLabelText() {
        return label.getText();
    }

    public String getText() {
        return textField.getText().trim();
    }

    public void setLabelText(String labelText) {
        label.setText(labelText);
    }

    public void setTextFieldText(String textFieldText) {
        textField.setText(textFieldText);
    }

}
