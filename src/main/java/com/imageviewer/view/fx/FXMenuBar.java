package com.imageviewer.view.fx;

import com.imageviewer.controller.command.CommandManager;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class FXMenuBar extends MenuBar {
    private final CommandManager commandManager;
    public FXMenuBar(CommandManager commandManager) {
        this.getMenus().add(createFileMenu());
        this.commandManager = commandManager;
    }

    private Menu createFileMenu() {
        Menu menu = new Menu("File");
        menu.getItems().add(createOpenMenuItem());
        menu.getItems().add(createExitMenuItem());
        return menu;
    }

    private MenuItem createExitMenuItem() {
        MenuItem menuItem = new MenuItem("Exit");
        menuItem.setOnAction(e -> System.exit(0));
        return menuItem;
    }

    private MenuItem createOpenMenuItem() {
        MenuItem menuItem = new MenuItem("Open");
        menuItem.setOnAction(e -> commandManager.execute("Open"));
        return menuItem;
    }
}
