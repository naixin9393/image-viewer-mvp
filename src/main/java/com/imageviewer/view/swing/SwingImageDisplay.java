package com.imageviewer.view.swing;

import com.imageviewer.model.ImageDisplay;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Map;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private int dragStartX;
    private final Map<String, Color> colors = Map.of(
            "RED", Color.RED,
            "GREEN", Color.GREEN,
            "BLUE", Color.BLUE,
            "YELLOW", Color.YELLOW,
            "ORANGE", Color.ORANGE
    );
    private Dragged dragged;
    private Released released;
    private final List<PaintCommand> paintCommands;

    public SwingImageDisplay() {
        this.paintCommands = new ArrayList<>();
        this.addMouseListener(mouseListener());
        this.addMouseMotionListener(mouseMotionListener());
    }

    private MouseMotionListener mouseMotionListener() {
        return new MouseMotionListener() {
            @Override
            public void mouseDragged(java.awt.event.MouseEvent e) {
                dragged.to(e.getX() - dragStartX);
            }

            @Override
            public void mouseMoved(java.awt.event.MouseEvent e) { }
        };
    }

    private MouseListener mouseListener() {
        return new MouseListener() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) { }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                dragStartX = e.getX();
                System.out.println("Pressed at " + dragStartX);
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                released.at(e.getX() - dragStartX);
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) { }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) { }
        };
    }

    @Override
    public int width() {
        return this.getWidth();
    }

    @Override
    public void clear() {
        paintCommands.clear();
        repaint();
    }

    @Override
    public void on(Released released) {
        this.released = released;
    }

    @Override
    public void on(Dragged dragged) {
        this.dragged = dragged;
    }

    @Override
    public void paint(String image, int offset) {
        paintCommands.add(new PaintCommand(image, offset));
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        for (PaintCommand paintCommand : paintCommands) {
            g.drawImage(loadImage(paintCommand.image), paintCommand.offset, 0, null);
        }
    }

    private BufferedImage loadImage(String url) {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        graphics.setColor(colorOf(url));
        graphics.fillRect(0, 0, getWidth(), getHeight());
        return image;
    }

    private Color colorOf(String url) {
        return colors.get(url);
    }

    private record PaintCommand(String image, int offset) { }
}
