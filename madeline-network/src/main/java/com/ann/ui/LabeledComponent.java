package com.ann.ui;

import javax.swing.*;
import java.awt.*;

public class LabeledComponent extends JPanel {

    private JLabel label;
    private JComponent component;

    public LabeledComponent(String labelText, JComponent component) {
        this.label = new JLabel(labelText);
        this.component = component;

        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));

        add(label);
        add(component);
    }

    public Object getValue() {
        return null;
    }

    public String getLabelText() {
        return label.getText();
    }

    public void setLabelText(String labelText) {
        label.setText(labelText);
    }

    public JComponent getComponent() {
        return component;
    }

    public void setComponent(JComponent component) {
        remove(this.component);
        this.component = component;
        add(component);
        revalidate();
        repaint();
    }

}
