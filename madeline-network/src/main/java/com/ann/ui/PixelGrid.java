package com.ann.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.ann.util.Vector;

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
        return pixels[row][col].getBackground().equals(Color.BLACK) ? 1 : 0;
    }

    public Vector getGridData() {
        Vector v = Vector.zeros(size * size);
        int k = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                v.set(k++, getPixelValue(i, j));
            }
        }

        return v;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                pixels[i][j].setBackground(Color.WHITE);
            }
        }
    }
}