package com.imageviewer.model;

public interface Image {
    String url();
    Image prev();
    Image next();
}
