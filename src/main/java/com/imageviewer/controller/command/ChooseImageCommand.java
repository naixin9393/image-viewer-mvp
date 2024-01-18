package com.imageviewer.controller.command;

import com.imageviewer.model.ImageChooser;

public class ChooseImageCommand implements Command {
    private final ImageChooser imageChooser;

    public ChooseImageCommand(ImageChooser imageChooser) {
        this.imageChooser = imageChooser;
    }

    @Override
    public void execute() {
        imageChooser.choose();
    }
}
