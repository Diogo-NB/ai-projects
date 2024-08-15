package com.hebbmodel;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TrainingFrame mainFrame = new TrainingFrame();
            mainFrame.setTitle("Basic ANN implementation");
            mainFrame.setVisible(true);
        });
    }
}
