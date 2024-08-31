package com.ann;

import javax.swing.*;
import com.ann.ui.*;

public class Main {

    public static void main(String[] args) {
        ConfigurationFrame configFrame = new ConfigurationFrame();

        JButton btn3 = new JButton("Next");
        configFrame.add(btn3);

        configFrame.setVisible(true);

        btn3.addActionListener(e -> {
            TrainingFrame mainFrame = new TrainingFrame();
            mainFrame.setTitle("Madeline Network");
            mainFrame.setVisible(true);
            
            mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    configFrame.setEnabled(true);
                    configFrame.setFocusableWindowState(true);
                }
            });

            configFrame.setEnabled(false);
            configFrame.setFocusableWindowState(false);
        });
    }
}
