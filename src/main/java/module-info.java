module image.viewer {
    requires javafx.controls;
    requires java.desktop;

    exports com.imageviewer.view.fx;
    exports com.imageviewer.model;
    exports com.imageviewer.presenter;
}