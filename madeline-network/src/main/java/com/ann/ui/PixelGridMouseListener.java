package com.ann.ui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class PixelGridMouseListener extends MouseAdapter {

    private boolean isPressed = false;
    private Color lastColorSet;

    @Override
    public void mousePressed(MouseEvent e) {
        isPressed = true;
        JPanel pixel = (JPanel) e.getSource();

        boolean isRightClick = e.getButton() == MouseEvent.BUTTON3;

        if (isRightClick) {
            pixel.setBackground(Color.WHITE);
            lastColorSet = Color.WHITE;
        } else if (lastColorSet == null) {
            pixel.setBackground(Color.BLACK);
            lastColorSet = Color.BLACK;
        } else {
            pixel.setBackground(lastColorSet);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isPressed = false;
        lastColorSet = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (isPressed) {
            mousePressed(e);
        }
    }
}