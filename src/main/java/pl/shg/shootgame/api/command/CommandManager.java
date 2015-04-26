/*
 * Copyright (C) 2014 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.shootgame.api.command;

import java.util.ArrayList;
import java.util.List;
import pl.shg.shootgame.api.command.def.ServerCommand;
import pl.shg.shootgame.api.command.def.ServersCommand;
import pl.shg.shootgame.api.command.def.ShgCommand;
import pl.shg.shootgame.api.command.def.StaffCommand;
import pl.shg.shootgame.api.command.def.StatsCommand;
import pl.shg.shootgame.api.command.def.XpCommand;

/**
 *
 * @author Aleksander
 */
public class CommandManager {
    private static  final List<Command> commands = new ArrayList<>();
    
    public static Command getCommand(String command) {
        for (Command cmd : commands) {
            if (cmd.getName().toLowerCase().equals(command.toLowerCase())) {
                return cmd;
            }
        }
        return null;
    }
    
    public static List<Command> getCommands() {
        return commands;
    }
    
    public static void register(Command command) {
        commands.add(command);
    }
    
    public static void registerDefaults() {
        register(new ServerCommand());
        register(new ServersCommand());
        register(new ShgCommand());
        register(new StaffCommand());
        register(new StatsCommand());
        register(new XpCommand());
    }
}
