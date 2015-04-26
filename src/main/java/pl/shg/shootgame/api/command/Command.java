/*
 * Copyright (C) 2014 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.shootgame.api.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import pl.shg.shootgame.api.Color;
import pl.shg.shootgame.api.ShootGame;
import pl.shg.shootgame.api.util.Validate;

/**
 *
 * @author Aleksander
 */
public abstract class Command {
    private final String description;
    private char[] flags;
    private String name;
    private String usage;
    
    public enum CommandMessage {
        NO_PERMISSION, PLAYER_NEEDED, NUMBER_NEEDED, CONSOLE_NEEDED;
    }
    
    public Command(String name) {
        Validate.notNull(name, "name can not be null");
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
        Validate.notNull(sender, "sender can not be null");
        Validate.notNull(permission, "permission can not be null");
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
        Validate.notNull(args, "args can not be null");
        Validate.notNull(flag, "flag can not be null");
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
        Validate.notNegative(index, "index must be positive (or zero)");
        Validate.notNull(args, "args can not be null");
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
    
    public ShootGame getShoot() {
        return ShootGame.getShoot();
    }
    
    public String getUsage() {
        return this.usage;
    }
    
    public boolean hasFlag(String[] args, char flag) {
        Validate.notNull(args, "args can not be null");
        Validate.notNull(flag, "flag can not be null");
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
        Validate.notNull(string, "string can not be null");
        try {
            return Integer.valueOf(string);
        } catch (NumberFormatException ex) {
            return def;
        }
    }
    
    public void setFlags(char[] flags) {
        Validate.notNull(flags, "flags can not be null");
        this.flags = flags;
    }
    
    public final void setUsage(String usage) {
        if (usage == null) {
            usage = "/" + this.getName();
        }
        this.usage = usage;
    }
    
    public void throwMessage(CommandSender sender, CommandMessage message) {
        Validate.notNull(sender, "sender can not be null");
        Validate.notNull(message, "message can not be null");
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
    
    public static String getTitle(String title, String info) {
        Validate.notNull(title, "title can not be null");
        String label = Color.RED + "" + Color.STRIKETHROUGH + "-------------" + ChatColor.RESET;
        if (info == null) {
            info = "";
        } else {
            info = info + " " + Color.RESET;
        }
        return label + " " + Color.DARK_AQUA + title + Color.RESET + " " + info + label;
    }
}
