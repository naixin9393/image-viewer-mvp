package com.imageviewer.view.fx;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class FXImageViewer extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Image Viewer");
        stage.getIcons().add(new Image("icon.png"));
        stage.show();
    }
}
