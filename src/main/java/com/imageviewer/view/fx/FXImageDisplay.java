package com.imageviewer.view.fx;

import com.imageviewer.model.ImageDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import static javafx.scene.paint.Color.RED;

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
        WritableImage writableImage = new WritableImage(500, 500);
        fillWith(writableImage, image);
        this.setImage(writableImage);
    }

    private static void fillWith(WritableImage writableImage, String color) {
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 500; j++) {
                pixelWriter.setColor(i, j, RED);
            }
        }
    }

    @Override
    public void on(Dragged dragged) {

    }

    @Override
    public void on(Released released) {

    }
}
