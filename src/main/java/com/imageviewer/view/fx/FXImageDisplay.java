package com.imageviewer.view.fx;

import com.imageviewer.model.ImageDisplay;
import javafx.scene.image.ImageView;

public class FXImageDisplay extends ImageView implements ImageDisplay {
    @Override
    public int width() {
        return (int) this.getFitWidth();
    }

    @Override
    public void clear() {
    }

    @Override
    public void paint(String image, int offset) {

    }

    @Override
    public void on(Dragged dragged) {

    }

    @Override
    public void on(Released released) {

    }
}
