package com.imageviewer.model.mock;

import com.imageviewer.model.Image;
import com.imageviewer.model.ImageLoader;

public class MockImageLoader implements ImageLoader {
    private final String[] imagesUrl = {
            "RED",
            "BLUE",
            "YELLOW",
            "GREEN",
            "ORANGE"
    };

    @Override
    public Image load() {
        return imageAt(0);
    }

    private Image imageAt(int i) {
        return new Image() {
            @Override
            public String url() {
                return imagesUrl[i];
            }

            @Override
            public Image prev() {
                return imageAt((i - 1 + imagesUrl.length) % imagesUrl.length);
            }

            @Override
            public Image next() {
                return imageAt((i + 1) % imagesUrl.length);
            }
        };
    }
}
