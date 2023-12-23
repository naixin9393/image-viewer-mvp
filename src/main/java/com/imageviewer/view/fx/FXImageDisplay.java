package com.imageviewer.view.fx;

import com.imageviewer.model.ImageDisplay;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.File;

public class FXImageDisplay extends Pane implements ImageDisplay {
    private final Scene scene;
    private Released released;
    private Dragged dragged;
    private double startDragX;

    public FXImageDisplay(Scene scene) {
        this.scene = scene;
        this.setMouseEventHandlers();
        this.getChildren().add(createLeftImageView());
        this.getChildren().add(createCenterImageView());
        this.getChildren().add(createRightImageView());
    }

    private Node createCenterImageView() {
        ImageView imageView = new ImageView();
        imageView.setPreserveRatio(true);
        return imageView;
    }

    private Node createRightImageView() {
        ImageView imageView = new ImageView();
        imageView.setPreserveRatio(true);
        return imageView;
    }

    private Node createLeftImageView() {
        ImageView imageView = new ImageView();
        imageView.setPreserveRatio(true);
        return imageView;
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
        Image image = new Image(new File(imageUrl).toURI().toString());
        ImageView imageView = new ImageView(image);
        rescale(imageView, image);
        setLocation(imageView, offset);
        getChildren().add(imageView);
    }

    private void rescale(ImageView imageView, Image image) {
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(Math.min(scene.getHeight(), image.getHeight()));
        imageView.setFitWidth(Math.min(scene.getWidth(), image.getWidth()));
    }

    private void setLocation(ImageView imageView, int offset) {
        imageView.setLayoutY(centerY(imageView));
        imageView.setLayoutX(centerX(imageView, offset));
    }

    private double centerY(ImageView imageView) {
        return (scene.getHeight() - imageView.getBoundsInLocal().getHeight()) / 2;
    }

    private double centerX(ImageView imageView, int offset) {
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
}
