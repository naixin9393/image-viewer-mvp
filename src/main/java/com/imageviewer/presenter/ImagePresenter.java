package com.imageviewer.presenter;

import com.imageviewer.model.FileImageLoader;
import com.imageviewer.model.Image;
import com.imageviewer.model.ImageChooser;
import com.imageviewer.model.ImageDisplay;
import com.imageviewer.model.ImageDisplay.Released;
import com.imageviewer.model.ImageDisplay.Dragged;

import java.io.File;

public class ImagePresenter {
    private Image image;
    private final ImageDisplay imageDisplay;

    public ImagePresenter(Image image, ImageDisplay imageDisplay, ImageChooser imageChooser) {
        this.image = image;
        this.imageDisplay = imageDisplay;

        this.imageDisplay.on((Dragged) this::dragged);
        this.imageDisplay.on((Released) this::released);
        this.imageDisplay.on(this::changedSize);
        imageChooser.on(this::chooseImage);
        this.imageDisplay.paint(image.url(), 0);
    }

    private void changedSize() {
        imageDisplay.clear();
        imageDisplay.paint(image.url(), 0);
    }

    private void chooseImage(String url) {
        image = new FileImageLoader(new File(url)).load();
        imageDisplay.clear();
        imageDisplay.paint(image.url(), 0);
    }

    private void released(int offset) {
        image = calculateNewReleaseImage(offset);
        imageDisplay.clear();
        imageDisplay.paint(image.url(), 0);
    }

    private Image calculateNewReleaseImage(int offset) {
        Image newImage = new FileImageLoader(new File(image.url())).load();
        int range = Math.abs(offset) / imageDisplay.width();
        range += Math.abs(offset) % imageDisplay.width() > imageDisplay.width() / 2 ? 1 : 0;
        for (int i = 0; i < range; i++)
            newImage = goingToRight(offset) ? newImage.next() : newImage.prev();
        return newImage;
    }

    private Image calculateNewDragImage(int offset) {
        Image newImage = new FileImageLoader(new File(image.url())).load();
        for (int i = 0; i < Math.abs(offset) / imageDisplay.width(); i++) {
            newImage = goingToRight(offset) ? newImage.next() : newImage.prev();
        }
        return newImage;
    }

    private void dragged(int offset) {
        imageDisplay.clear();
        Image newImage1 = calculateNewDragImage(offset);
        int newOffset = offset + (int) Math.signum(offset) * imageDisplay.width();
        Image newImage2 = calculateNewDragImage(newOffset);
        imageDisplay.paint(newImage1.url(), offset % imageDisplay.width());
        imageDisplay.paint(newImage2.url(), goingToRight(newOffset) ? imageDisplay.width() + newOffset % imageDisplay.width() : newOffset % imageDisplay.width() - imageDisplay.width());
    }

    private static boolean goingToRight(int offset) {
        return offset < 0;
    }
}
