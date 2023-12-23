package com.imageviewer.view.fx;

import com.imageviewer.model.FileImageLoader;
import com.imageviewer.presenter.Presenter;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;

public class FXImageViewer extends Application {

    private Scene primaryScene;
    private FXImageDisplay imageDisplay;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Image Viewer");
        primaryStage.getIcons().add(new Image("icon.png"));
        primaryStage.setScene(createPrimaryScene());

        createPresenter();

        primaryStage.show();
    }

    private void createPresenter() {
        com.imageviewer.model.Image sampleImage = new FileImageLoader(new File("src/main/resources/sample")).load();
        new Presenter(sampleImage, imageDisplay);
    }

    private Scene createPrimaryScene() {
        this.primaryScene = new Scene( new VBox(), 800, 600);
        this.primaryScene.setRoot(createRoot());
        return primaryScene;
    }

    private Parent createRoot() {
        VBox layout = new VBox();
        layout.setStyle("-fx-background-color: #000000");
        layout.getChildren().add(createImageDisplay());
        return layout;
    }

    private Node createImageDisplay() {
        this.imageDisplay = new FXImageDisplay(primaryScene);
        return imageDisplay;
    }
}
