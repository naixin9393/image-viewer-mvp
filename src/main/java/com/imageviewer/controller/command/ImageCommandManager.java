package com.imageviewer.controller.command;

import java.util.HashMap;
import java.util.Map;

public class ImageCommandManager implements CommandManager {
    private static final ImageCommandManager instance = new ImageCommandManager();

    private ImageCommandManager() {}

    public static ImageCommandManager getInstance() {
        return instance;
    }

    private final Map<String, Command> commands = new HashMap<>();

    @Override
    public ImageCommandManager add(String name, Command command) {
        this.commands.put(name, command);
        return instance;
    }

    @Override
    public void execute(String name) {
        this.commands.get(name).execute();
    }
}
