package com.imageviewer.view.fx;

import com.imageviewer.model.mock.MockImageLoader;
import com.imageviewer.presenter.Presenter;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
        com.imageviewer.model.Image image = new MockImageLoader().load();
        new Presenter(image, imageDisplay);
    }

    private Scene createPrimaryScene() {
        this.primaryScene = new Scene( new VBox(), 800, 600);
        this.primaryScene.setRoot(createPrimaryLayout());
        return primaryScene;
    }

    private Parent createPrimaryLayout() {
        VBox layout = new VBox();
        layout.setStyle("-fx-background-color: #000000");
        layout.getChildren().add(createImageDisplay());
        //layout.setAlignment(javafx.geometry.Pos.CENTER);
        layout.setFillWidth(true);
        return layout;
    }

    private Node createImageDisplay() {
        this.imageDisplay = new FXImageDisplay(primaryScene);
        return imageDisplay;
    }
}
