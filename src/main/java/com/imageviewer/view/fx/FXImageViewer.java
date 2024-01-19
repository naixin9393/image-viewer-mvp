package com.imageviewer.view.fx;

import com.imageviewer.controller.command.CommandManager;
import com.imageviewer.model.FXImageChooser;
import com.imageviewer.model.ImageChooser;
import com.imageviewer.model.ImageDisplay;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FXImageViewer extends Application {
    private Scene primaryScene;
    private FXImageDisplay imageDisplay;
    private final CommandManager commandManager;

    public FXImageViewer(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Image Viewer");
        primaryStage.getIcons().add(new Image("icon.png"));
        primaryStage.setScene(createPrimaryScene());

        primaryStage.fireEvent(new StageEvent(StageEvent.READY));

        primaryStage.show();
    }

    class StageEvent extends Event {
        public StageEvent(EventType<? extends Event> eventType) {
            super(eventType);
        }
        public static final EventType<StageEvent> READY = new EventType<>(Event.ANY, "STAGE_READY");
        public ImageDisplay getImageDisplay() {
            return imageDisplay;
        }
        public ImageChooser getImageChooser() {
            return new FXImageChooser();
        }
    }

    private Scene createPrimaryScene() {
        this.primaryScene = new Scene(new VBox(), 800, 600);
        this.primaryScene.setRoot(createRoot());
        return primaryScene;
    }

    private Parent createRoot() {
        VBox layout = new VBox();
        layout.setStyle("-fx-background-color: #000000");
        layout.getChildren().add(createMenuBar());
        layout.getChildren().add(createImageDisplay());
        return layout;
    }

    private Node createMenuBar() {
        return new FXMenuBar(commandManager);
    }

    private Node createImageDisplay() {
        this.imageDisplay = new FXImageDisplay(primaryScene);
        return imageDisplay;
    }
}
