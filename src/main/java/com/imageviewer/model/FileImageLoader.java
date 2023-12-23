package com.imageviewer.model;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;

public class FileImageLoader implements ImageLoader {
    private final List<String> imageExtensions = List.of("jpeg", "jpg", "png");
    private final String[] images;
    private final File originalFile;

    public FileImageLoader(File file) {
        this.originalFile = file;
        if (file.isDirectory())
            this.images = toString(file.listFiles(withImageExtension()));
        else
            this.images = toString(new File(file.getParent()).listFiles(withImageExtension()));
    }

    private String[] toString(File[] files) {
        return Arrays.stream(files)
                .map(File::getAbsolutePath)
                .toList()
                .toArray(new String[0]);
    }

    @Override
    public Image load() {
        if (originalFile.isDirectory())
            return imageAt(0);
        else
            return searchFile(originalFile);
    }

    private Image searchFile(File originalFile) {
        for (int i = 0; i < images.length; i++) {
            if (images[i].endsWith(originalFile.getName()))
                return imageAt(i);
        }
        throw new RuntimeException("File not found");
    }

    private Image imageAt(int i) {
        return new Image(){

            @Override
            public String url() {
                return images[i];
            }

            @Override
            public Image next() {
                return i + 1 == images.length ? null : imageAt(i + 1);
            }

            @Override
            public Image prev() {
                return i == 0 ? null : imageAt(i - 1);
            }
        };
    }

    private FileFilter withImageExtension() {
        return f -> isImageFile(f.getName());
    }

    private boolean isImageFile(String name) {
        return imageExtensions.stream()
                .anyMatch(name::endsWith);
    }
}
