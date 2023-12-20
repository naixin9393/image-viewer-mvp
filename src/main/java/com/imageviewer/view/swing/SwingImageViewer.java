package com.imageviewer.view.swing;

import com.imageviewer.model.ImageDisplay;

import javax.swing.*;
import java.awt.*;

public class SwingImageViewer extends JFrame {
    private ImageDisplay imageDisplay;

    public SwingImageViewer() throws HeadlessException {
        this.setTitle("Image Viewer");
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(createImageDisplay());
    }

    private Component createImageDisplay() {
        SwingImageDisplay display = new SwingImageDisplay();
        this.imageDisplay = display;
        return display;
    }

    public ImageDisplay imageDisplay() {
        return imageDisplay;
    }
}
