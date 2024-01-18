package com.imageviewer.model;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;

public class FXImageChooser implements ImageChooser {
    private ChosenImage chosenImage;
    public void choose() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Images", "*.jpg", "*.png", "*.jpeg"));
        File file = fileChooser.showOpenDialog(null);
        if (file == null) return;
        chosenImage.get(file.getAbsolutePath());
    }

    @Override
    public void on(ChosenImage chosenImage) {
        this.chosenImage = chosenImage;
    }
}
