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
    private final WritableImage writableImage;

    public FXImageDisplay(Scene scene) {
        this.scene = scene;
        this.writableImage = new WritableImage((int) scene.getWidth(), (int) scene.getHeight());
        this.setMouseEventHandlers();
    }

    private void setMouseEventHandlers() {
        this.setOnMousePressed(event -> startDragX = event.getX());
        this.setOnMouseReleased(event ->
            released.at((int) (event.getX() - startDragX))
        );
        this.setOnMouseDragged(event ->
            dragged.to((int) (event.getX() - startDragX))
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
        fillWith(writableImage, image, offset);
        this.setImage(writableImage);
    }

    private void fillWith(WritableImage writableImage, String color, int offset) {
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        for (int i = Math.max(offset, 0); i < Math.min(scene.getWidth() + offset, scene.getWidth()); i++) {
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
