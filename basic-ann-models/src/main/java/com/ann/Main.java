package com.ann;

import javax.swing.SwingUtilities;

import com.ann.ui.TrainingFrame;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TrainingFrame mainFrame = new TrainingFrame();
            mainFrame.setTitle("Basic ANN implementation");
            mainFrame.setVisible(true);
        });
    }
}
