package com.hebbmodel;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class PixelGridMouseListener extends MouseAdapter {

    private boolean isPressed = false;
    private Color currentColor = Color.WHITE;

    private void togglePixelColor(JPanel pixel) {
        if (currentColor == null) {
            currentColor = pixel.getBackground();
        }

        Color newColor = currentColor.equals(Color.WHITE) ? Color.BLACK : Color.WHITE;

        pixel.setBackground(newColor);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        isPressed = true;

        JPanel pixel = (JPanel) e.getSource();
        togglePixelColor(pixel);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        currentColor = null;
        isPressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (isPressed) {
            JPanel pixel = (JPanel) e.getSource();
            togglePixelColor(pixel);
        }
    }
}