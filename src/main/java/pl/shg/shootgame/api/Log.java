/*
 * Copyright (C) 2014 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.shootgame.api;

import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.shg.shootgame.api.util.Validate;

/**
 *
 * @author Aleksander
 */
public class Log {
    public static void admins(String message) {
        Validate.notNull(message, "message can not be null");
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission("shootgame.staff")) {
                player.sendMessage(Color.DARK_AQUA + "[" + Color.AQUA + "INFO" + Color.DARK_AQUA + "] " + Color.YELLOW + message);
            }
        }
    }
    
    public static void info(String message) {
        Validate.notNull(message, "message can not be null");
        log(Level.INFO, message);
    }
    
    public static void log(Level level, String message) {
        Validate.notNull(level, "level can not be null");
        Validate.notNull(message, "message can not be null");
        Bukkit.getLogger().log(level, message);
    }
    
    public static void severe(String message) {
        Validate.notNull(message, "message can not be null");
        log(Level.INFO, message);
    }
    
    public static void warning(String message) {
        Validate.notNull(message, "message can not be null");
        log(Level.WARNING, message);
    }
}
