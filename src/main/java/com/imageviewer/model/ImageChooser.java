package com.imageviewer.model;

public interface ImageChooser {
    void choose();
    void on(ChosenImage chosenImage);

    interface ChosenImage {
        void get(String image);
    }
}
