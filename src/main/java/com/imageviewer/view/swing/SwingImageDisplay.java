package com.imageviewer.view.swing;

import com.imageviewer.model.ImageDisplay;

import javax.swing.*;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    @Override
    public int width() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public void paint(String image, int offset) {

    }

    @Override
    public void on(Dragged dragged) {

    }

    @Override
    public void on(Released released) {

    }
}
