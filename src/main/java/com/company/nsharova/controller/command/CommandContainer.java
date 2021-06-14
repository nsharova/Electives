package com.company.nsharova.controller.command;

import com.company.nsharova.controller.command.impl.NoCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandContainer {

    private static final Map<String, Command> commands = new HashMap<>();
    private static final Command noCommand = new NoCommand();

    public static void register(Command command) {
        commands.put(command.getName(), command);
    }

    public static Command get(String commandName) {
        return Optional.ofNullable(commandName)
                .map(commands::get)
                .orElse(noCommand);
    }
}
