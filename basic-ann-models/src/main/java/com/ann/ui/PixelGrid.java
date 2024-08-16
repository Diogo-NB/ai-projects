package com.ann.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PixelGrid extends JPanel {
    private JPanel[][] pixels = new JPanel[10][10]; // 2D array to store pixel panels

    public PixelGrid(String title) {
        this(new JLabel(title));
    }

    public PixelGrid() {
        this("");
    }

    public PixelGrid(Component titleComponent) {
        super();
        add(titleComponent);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(10, 10));
        MouseListener listener = new PixelGridMouseListener();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JPanel pixel = new JPanel();
                pixel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                pixel.setBackground(Color.WHITE);

                pixel.addMouseListener(listener);

                grid.add(pixel);
                pixels[i][j] = pixel;
            }
        }

        add(grid);
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            clearGrid();
        });

        add(clearButton, BorderLayout.EAST);
    }

    public int getPixelValue(int row, int col) {
        return pixels[row][col].getBackground().equals(Color.WHITE) ? 1 : -1;
    }

    public int[][] getGridData() {
        int[][] gridData = new int[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                gridData[i][j] = getPixelValue(i, j);
            }
        }

        return gridData;
    }

    public void clearGrid() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                pixels[i][j].setBackground(Color.WHITE);
            }
        }
    }
}