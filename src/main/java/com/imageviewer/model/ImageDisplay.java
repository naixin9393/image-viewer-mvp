package com.imageviewer.model;

public interface ImageDisplay {
    int width();
    void clear();
    void paint(String image, int offset);

    void on(Dragged dragged);
    void on(Released released);
    void on(ChangedSize changedSize);

    interface Dragged {
        void to(int offset);
    }

    interface Released {
        void at(int offset);
    }

    interface ChangedSize {
        void changedSize();
    }
}
