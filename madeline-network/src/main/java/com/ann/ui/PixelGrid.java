package com.ann.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PixelGrid extends JPanel {

    private int size;
    private JPanel[][] pixels;

    public PixelGrid(int size) {
        super();
        this.size = size;
        pixels = new JPanel[size][size];

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(size, size));
        MouseListener listener = new PixelGridMouseListener();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JPanel pixel = new JPanel();
                pixel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                pixel.setBackground(Color.WHITE);

                pixel.addMouseListener(listener);

                grid.add(pixel);
                pixels[i][j] = pixel;
            }
        }

        add(grid);
    }

    public int getGridSize() {
        return size;
    }

    public int getPixelValue(int row, int col) {
        return pixels[row][col].getBackground().equals(Color.WHITE) ? 1 : -1;
    }

    public int[][] getGridData() {
        int[][] gridData = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                gridData[i][j] = getPixelValue(i, j);
            }
        }

        return gridData;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                pixels[i][j].setBackground(Color.WHITE);
            }
        }
    }
}