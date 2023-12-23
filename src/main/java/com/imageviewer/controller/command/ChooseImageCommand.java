package com.imageviewer.controller.command;

import com.imageviewer.model.Image;
import com.imageviewer.model.ImageChooser;
import com.imageviewer.model.ImageDisplay;

public class ChooseImageCommand implements Command {
    private final ImageDisplay imageDisplay;
    private final ImageChooser imageChooser;

    public ChooseImageCommand(ImageChooser imageChooser, ImageDisplay imageDisplay) {
        this.imageDisplay = imageDisplay;
        this.imageChooser = imageChooser;
    }

    @Override
    public void execute() {
        Image image = imageChooser.choose();
        if (image == null) return;
        imageDisplay.clear();
        imageDisplay.paint(image.url(), 0);
    }
}
