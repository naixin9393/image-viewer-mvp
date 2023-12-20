package com.imageviewer.model.presenter;

import com.imageviewer.model.Image;
import com.imageviewer.model.ImageDisplay;
import com.imageviewer.model.ImageDisplay.Released;
import com.imageviewer.model.ImageDisplay.Dragged;

public class Presenter {
    private Image image;
    private final ImageDisplay imageDisplay;

    public Presenter(Image image, ImageDisplay imageDisplay) {
        this.image = image;
        this.imageDisplay = imageDisplay;

        //this.imageDisplay.on((Dragged) offset -> dragged(offset));
        this.imageDisplay.on((Dragged) this::dragged);
        this.imageDisplay.on((Released) this::released);
        this.imageDisplay.paint(image.url(), 0);
    }

    private void released(int offset) {
        if (shouldChangeWith(offset)) {
            image = goingToRight(offset) ? image.next() : image.prev();
        }
        imageDisplay.clear();
        imageDisplay.paint(image.url(), 0);
    }

    private void dragged(int offset) {
        imageDisplay.clear();
        image = goingToRight(offset) ? image.next() : image.prev();
        imageDisplay.paint(image.url(), goingToRight(offset) ? imageDisplay.width(): - imageDisplay.width());
    }

    private static boolean goingToRight(int offset) {
        return offset > 0;
    }

    private boolean shouldChangeWith(int offset) {
        return offset > imageDisplay.width() / 2;
    }
}
