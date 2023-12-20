package com.imageviewer.model.mock;

import com.imageviewer.model.Image;

public class Main {
    public static void main(String[] args) {
        Image image = new MockImageLoader().load();
        for (int i = 0; i < 5; i++) {
            System.out.println(image.url());
            image = image.next();
        }
    }
}
