package com.imageviewer.view.fx;

import com.imageviewer.model.ImageDisplay;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FXImageDisplay extends Pane implements ImageDisplay {
    private final Scene scene;
    private Released released;
    private Dragged dragged;
    private Changed changed;
    private ChangedSize changedSize;
    private double startDragX;
    private final List<Image> imageBuffer;

    public FXImageDisplay(Scene scene) {
        this.scene = scene;
        this.setSizeChangeEventHandler();
        this.setMouseEventHandlers();
        imageBuffer = new ArrayList<>();
    }

    private void setSizeChangeEventHandler() {
        scene.widthProperty().addListener((observable, oldValue, newValue) -> changedSize.changedSize());
        scene.heightProperty().addListener((observable, oldValue, newValue) -> changedSize.changedSize());
    }

    private void setMouseEventHandlers() {
        this.setOnMousePressed(event -> startDragX = event.getX());
        this.setOnMouseReleased(event -> released.at((int) (event.getX() - startDragX)));
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
        this.getChildren().clear();
    }

    @Override
    public void paint(String imageUrl, int offset) {
        Image image = contains(imageUrl) ? get(imageUrl) : add(imageUrl);
        ImageView imageView = new ImageView(image);
        if (image == null) return;
        rescale(imageView, image);
        setLocation(imageView, offset);
        getChildren().add(imageView);
        if (offset == 0) changed.to(imageUrl);
    }

    private boolean contains(String imageUrl) {
        return imageBuffer.stream().anyMatch(image -> image.getUrl().equals(new File(imageUrl).toURI().toString()));
    }

    private Image get(String imageUrl) {
        for (Image image : imageBuffer) {
            if (image.getUrl().equals(new File(imageUrl).toURI().toString())) return image;
        }
        return null;
    }

    private Image add(String imageUrl) {
        if (imageBuffer.size() > 5) imageBuffer.remove(0);
        Image image = new Image(new File(imageUrl).toURI().toString());
        imageBuffer.add(image);
        return image;
    }

    private void rescale(ImageView imageView, Image image) {
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(Math.min(scene.getHeight(), image.getHeight()));
        imageView.setFitWidth(Math.min(scene.getWidth(), image.getWidth()));
    }

    private void setLocation(ImageView imageView, int offset) {
        imageView.setLayoutY(centerY(imageView));
        imageView.setLayoutX(setX(imageView, offset));
    }

    private double centerY(ImageView imageView) {
        return (scene.getHeight() - imageView.getBoundsInLocal().getHeight()) / 2;
    }

    private double setX(ImageView imageView, int offset) {
        return (scene.getWidth() - imageView.getBoundsInLocal().getWidth()) / 2 + offset;
    }

    @Override
    public void on(Dragged dragged) {
        this.dragged = dragged;
    }

    @Override
    public void on(Released released) {
        this.released = released;
    }

    @Override
    public void on(Changed changed) {
        this.changed = changed;
    }

    @Override
    public void on(ChangedSize changedSize) {
        this.changedSize = changedSize;
    }
}
