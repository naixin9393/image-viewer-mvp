package com.imageviewer.view.fx;

import com.imageviewer.model.ImageDisplay;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class FXImageDisplay extends ImageView implements ImageDisplay {
    private final Scene scene;
    private Released released;
    private Dragged dragged;
    private double startDragX;

    public FXImageDisplay(Scene scene) {
        this.scene = scene;
        this.setMouseEventHandlers();
    }

    private void setMouseEventHandlers() {
        this.setOnMousePressed(event -> startDragX = event.getX());
        this.setOnMouseReleased(event ->
            released.at((int) (startDragX  - event.getX()))
        );

    }

    @Override
    public int width() {
        return (int) scene.getWidth();
    }

    @Override
    public void clear() {
    }

    @Override
    public void paint(String image, int offset) {
        WritableImage writableImage = new WritableImage((int) scene.getWidth(), (int) scene.getHeight());
        fillWith(writableImage, image);
        this.setImage(writableImage);
    }

    private void fillWith(WritableImage writableImage, String color) {
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        for (int i = 0; i < scene.getWidth(); i++) {
            for (int j = 0; j < scene.getHeight(); j++) {
                pixelWriter.setColor(i, j, Color.valueOf(color));
            }
        }
    }

    @Override
    public void on(Dragged dragged) {
        this.dragged = dragged;
    }

    @Override
    public void on(Released released) {
        this.released = released;
    }
}
