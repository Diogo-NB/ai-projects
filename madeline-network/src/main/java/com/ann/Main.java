package com.ann;

import java.awt.*;
import javax.swing.*;

import com.ann.ui.LabeledTextField;
import com.ann.ui.TrainingFrame;

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
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);

        LabeledTextField gridSizeField = new LabeledTextField("Grid size: ", "10");
        panel.add(gridSizeField, gbc);

        gbc.gridy++;
        JButton btn2 = new JButton("Button 2");
        btn2.addActionListener(e -> {
            System.out.println("Button 2 clicked");
        });
        panel.add(btn2, gbc);

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
