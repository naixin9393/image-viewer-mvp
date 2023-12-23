package com.imageviewer.controller.command;

public interface CommandManager {
    CommandManager add(String name, Command command);
    void execute(String name);
}
