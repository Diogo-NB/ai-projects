package com.ann.ui;

import java.awt.Dimension;
import javax.swing.JTextField;

public class EditLabelField extends JTextField {

    EditLabelField(String text) {
        super(text);
        setMaximumSize(new Dimension(100, 20));
        setHorizontalAlignment(JTextField.CENTER);
    }

    public String getLabel() {
        String text = getText().trim();

        if (text.isEmpty()) {
            text = "Grid #1";
        }
        return text;
    }

}
