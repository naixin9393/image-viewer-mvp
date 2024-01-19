package com.imageviewer.view.fx;

import com.imageviewer.controller.command.ChooseImageCommand;
import com.imageviewer.controller.command.CommandManager;
import com.imageviewer.controller.command.ImageCommandManager;
import com.imageviewer.model.FileImageLoader;
import com.imageviewer.model.ImageChooser;
import com.imageviewer.model.ImageDisplay;
import com.imageviewer.presenter.ImagePresenter;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Objects;

public class Main extends Application {
    private CommandManager commandManager;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.commandManager = ImageCommandManager.getInstance();
        FXImageViewer imageViewer = new FXImageViewer(commandManager);
        primaryStage.addEventHandler(FXImageViewer.StageEvent.READY, this::onStageReady);
        imageViewer.start(primaryStage);
    }

    private void onStageReady(FXImageViewer.StageEvent stageEvent) {
        ImageDisplay imageDisplay = stageEvent.getImageDisplay();
        ImageChooser imageChooser = stageEvent.getImageChooser();
        commandManager.add("Open", new ChooseImageCommand(imageChooser));
        createPresenter(imageDisplay, imageChooser);
    }

    private void createPresenter(ImageDisplay imageDisplay, ImageChooser imageChooser) {
        File sampleFile = getSampleFile();
        com.imageviewer.model.Image sampleImage = null;
        if (sampleFile != null) {
            sampleImage = new FileImageLoader(sampleFile).load();
        }
        new ImagePresenter(sampleImage, imageDisplay, imageChooser);
    }

    private File getSampleFile() {
        try {
            String sampleFileURL = System.getProperty("user.dir");
            URL res = new URL(Objects.requireNonNull(getClass().getResource("/sample")).toString());
            if (res.getProtocol().equals("jar"))
                return new File(sampleFileURL + "/app/classes/sample");
            return new File(sampleFileURL + "/src/main/resources/sample");
        } catch (Exception e) {
            return null;
        }
    }
}
