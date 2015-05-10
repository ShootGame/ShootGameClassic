/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.command;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import pl.shg.shootgame.command.ServerCommand;
import pl.shg.shootgame.command.ServersCommand;
import pl.shg.shootgame.command.ShgCommand;
import pl.shg.shootgame.command.StaffCommand;
import pl.shg.shootgame.command.StatsCommand;
import pl.shg.shootgame.command.XpCommand;

/**
 *
 * @author Aleksander
 */
public abstract class CommandBase {
    private static  final List<CommandBase> commands = new ArrayList<>();
    
    private final String description;
    private char[] flags;
    private String name;
    private String usage;
    
    public enum CommandMessage {
        NO_PERMISSION, PLAYER_NEEDED, NUMBER_NEEDED, CONSOLE_NEEDED;
    }
    
    public CommandBase(String name) {
        PluginCommand command = Bukkit.getPluginCommand(name.toLowerCase());
        if (command != null) {
            this.description = command.getDescription();
            this.name = command.getName();
            this.usage = command.getUsage();
        } else {
            throw new NullPointerException("Command " + name + " doesnt exists in the Bukkit API");
        }
    }
    
    public boolean canNot(CommandSender sender, String permission) {
        if (!sender.hasPermission(permission)) {
            this.throwMessage(sender, CommandMessage.NO_PERMISSION);
            return true;
        } else {
            return false;
        }
    }
    
    public abstract void execute(CommandSender sender, String[] args);
    
    public String getDescription() {
        return this.description;
    }
    
    public String getFlagValue(String[] args, char flag) {
        return this.getFlagValue(args, flag, null);
    }
    
    public String getFlagValue(String[] args, char flag, String def) {
        for (String arg : args) {
            arg = arg.toLowerCase();
            String split = null;
            if (arg.startsWith("-" + flag)) {
                if (arg.contains("=")) {
                    split = "=";
                } else if (arg.contains(":")) {
                    split = ":";
                }
            }
            
            if (split != null) {
                String[] list = arg.split(split, 2);
                if (list.length > 1) {
                    return list[1];
                }
            }
            return def;
        }
        return def;
    }
    
    public char[] getFlags() {
        return this.flags;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getStringFromArgs(int index, String[] args) {
        StringBuilder builder = new StringBuilder();
        for (int i = index; i < args.length; i++) {
            if (!this.isFlag(args[i].toLowerCase())) {
                builder.append(args[i]).append(" ");
            }
        }
        
        try {
            return builder.toString().substring(0, builder.toString().length() - 1);
        } catch (StringIndexOutOfBoundsException ex) {
            return builder.toString();
        }
    }
    
    public String getUsage() {
        return this.usage;
    }
    
    public boolean hasFlag(String[] args, char flag) {
        for (String arg : args) {
            if (arg.toLowerCase().startsWith("-" + flag)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isFlag(String arg) {
        if (!arg.toLowerCase().startsWith("-") || this.getFlags() == null) {
            return false;
        }
        for (char flag : this.getFlags()) {
            if (arg.substring(1).equalsIgnoreCase(String.valueOf(flag))) {
                return true;
            }
        }
        return false;
    }
    
    public abstract int minArguments();
    
    public int parseInteger(String string, int def) {
        try {
            return Integer.valueOf(string);
        } catch (NumberFormatException ex) {
            return def;
        }
    }
    
    public void setFlags(char[] flags) {
        this.flags = flags;
    }
    
    public final void setUsage(String usage) {
        if (usage == null) {
            usage = "/" + this.getName();
        }
        this.usage = usage;
    }
    
    public void throwMessage(CommandSender sender, CommandMessage message) {
        String result = null;
        switch (message) {
            case NO_PERMISSION: result = "Nie posiadasz uprawnien do tej komendy (lub podanego argumentu)."; break;
            case PLAYER_NEEDED: result = "Bledny wysylajacy; ta komende musi wykonac gracz."; break;
            case NUMBER_NEEDED: result = "Podany przez Ciebie argument musi byc liczba!"; break;
            case CONSOLE_NEEDED: result = "Bledny wysylajacy; ta komende trzeba wykonac z konsoli serwera."; break;
        }
        if (result != null) {
            sender.sendMessage(ChatColor.RED + result);
        }
    }
    
    public static CommandBase getCommand(String command) {
        for (CommandBase cmd : commands) {
            if (cmd.getName().toLowerCase().equals(command.toLowerCase())) {
                return cmd;
            }
        }
        return null;
    }
    
    public static List<CommandBase> getCommands() {
        return commands;
    }
    
    public static String getTitle(String title, String info) {
        String label = ChatColor.RED + "" + ChatColor.STRIKETHROUGH + "-------------" + ChatColor.RESET;
        if (info == null) {
            info = "";
        } else {
            info = info + " " + ChatColor.RESET;
        }
        return label + " " + ChatColor.DARK_AQUA + title + ChatColor.RESET + " " + info + label;
    }
    
    public static void register(CommandBase command) {
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
