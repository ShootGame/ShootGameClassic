/*
 * Copyright (C) 2014 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.shootgame.api;

import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 *
 * @author Aleksander
 */
@Deprecated
public class Log {
    public static void admins(String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission("shootgame.staff")) {
                player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + "INFO" + ChatColor.DARK_AQUA + "] " + ChatColor.YELLOW + message);
            }
        }
    }
    
    public static void info(String message) {
        log(Level.INFO, message);
    }
    
    public static void log(Level level, String message) {
        Bukkit.getLogger().log(level, message);
    }
    
    public static void severe(String message) {
        log(Level.INFO, message);
    }
    
    public static void warning(String message) {
        log(Level.WARNING, message);
    }
}
