package com.hebbmodel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PixelGrid extends JPanel {
    private JPanel[][] pixels = new JPanel[10][10]; // 2D array to store pixel panels

    public PixelGrid() {
        setLayout(new GridLayout(10, 10));
        MouseListener listener = new PixelGridMouseListener();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JPanel pixel = new JPanel();
                pixel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                pixel.setBackground(Color.WHITE);

                pixel.addMouseListener(listener);

                add(pixel);
                pixels[i][j] = pixel; // Store the pixel panel in the array
            }
        }
    }

    public int getPixelValue(int row, int col) {
        return pixels[row][col].getBackground().equals(Color.WHITE) ? 1 : -1;
    }

    public int[][] getGridRepresentation() {
        int[][] gridRepresentation = new int[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                gridRepresentation[i][j] = getPixelValue(i, j);
            }
        }

        return gridRepresentation;
    }

    public void resetGrid() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                pixels[i][j].setBackground(Color.WHITE);
            }
        }
    }
}