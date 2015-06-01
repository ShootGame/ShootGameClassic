/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.command;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.help.GenericCommandHelpTopic;
import org.bukkit.help.HelpTopic;
import org.bukkit.help.HelpTopicComparator;
import org.bukkit.help.IndexHelpTopic;
import org.bukkit.plugin.Plugin;
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
    private static final List<CommandBase> commands = new ArrayList<>();
    private static final Executor executor = new Executor();
    
    private String[] aliases;
    private String description, permission, usage;
    private char[] flags;
    private Plugin owner;
    
    public enum CommandMessage {
        NO_PERMISSION, PLAYER_NEEDED, NUMBER_NEEDED, CONSOLE_NEEDED;
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
    
    public String[] getAliases() {
        return this.aliases;
    }
    
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
        return this.getAliases()[0];
    }
    
    public Plugin getOwner() {
        return this.owner;
    }
    
    public String getPermission() {
        return this.permission;
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
        if (this.usage != null) {
            return this.usage;
        }
        return "/" + this.getName();
    }
    
    public boolean hasFlag(String[] args, char flag) {
        for (String arg : args) {
            if (arg.toLowerCase().startsWith("-" + flag)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean hasPermission() {
        return this.permission != null;
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
    
    public void setAliases(String... aliases) {
        this.aliases = aliases;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setFlags(char[] flags) {
        this.flags = flags;
    }
    
    public void setPermission(String permission) {
        this.permission = permission;
    }
    
    public void setUsage(String usage) {
        if (usage == null) {
            usage = "/" + this.getName();
        }
        this.usage = "/" + this.getName() + " " + usage;
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
    
    private void setOwner(Plugin owner) {
        this.owner = owner;
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
    
    public static void register(Plugin plugin, CommandBase command) {
        command.setOwner(plugin);
        commands.add(command);
        registerBukkit(plugin, command);
    }
    
    public static void registerDefaults(Plugin plugin) {
        register(plugin, new ServerCommand());
        register(plugin, new ServersCommand());
        register(plugin, new ShgCommand());
//        register(plugin, new StaffCommand());
//        register(plugin, new StatsCommand());
//        register(plugin, new XpCommand());
    }
    
    private static void registerBukkit(Plugin plugin, CommandBase command) {
        Command bukkitCommand = new CommandPerformer(executor, command.getName());
        bukkitCommand.setAliases(Arrays.asList(command.getAliases()));
        bukkitCommand.setDescription(command.getDescription());
        bukkitCommand.setLabel(command.getName());
        if (command.hasPermission()) {
            bukkitCommand.setPermission(command.getPermission());
        }
        bukkitCommand.setUsage(command.getUsage());
        
        try {
            Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            if (field != null) {
                field.setAccessible(true);
                CommandMap map = (CommandMap) field.get(Bukkit.getServer());
                map.register(command.getName(), plugin.getName().toLowerCase(), bukkitCommand);
            }
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(CommandBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void registerHelpTopic(Plugin plugin) {
        Set<HelpTopic> help = new TreeSet<>(HelpTopicComparator.helpTopicComparatorInstance());
        for (CommandBase command : CommandBase.getCommands()) {
            if (!command.getOwner().equals(plugin)) {
                continue;
            }
            
            Command bukkit = Bukkit.getCommandMap().getCommand(command.getName());
            if (bukkit != null) {
                help.add(new GenericCommandHelpTopic(bukkit));
            }
        }
        
        IndexHelpTopic index = new IndexHelpTopic(
                plugin.getName(),
                "Wszystkie komendy pluginu " + plugin.getName(),
                null,
                help,
                "Ponizej znajduja sie wszystkie komendy " + plugin.getName() + ":"
        );
        
        Bukkit.getHelpMap().addTopic(index);
    }
}
