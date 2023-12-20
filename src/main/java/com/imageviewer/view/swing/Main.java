package com.imageviewer.view.swing;

import com.imageviewer.model.Image;
import com.imageviewer.model.mock.MockImageLoader;
import com.imageviewer.model.presenter.Presenter;

public class Main {
    public static void main(String[] args) {
        Image image = new MockImageLoader().load();
        SwingImageViewer imageViewer = new SwingImageViewer();
        new Presenter(image, imageViewer.imageDisplay());
        imageViewer.setVisible(true);
    }
}
