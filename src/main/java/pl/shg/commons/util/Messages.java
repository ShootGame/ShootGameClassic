/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.util;

import org.bukkit.ChatColor;

/**
 *
 * @author Aleksander
 */
public class Messages {
    public static final char CHAR = '`';
    
    public static String translate(String string) {
        return translate(string, CHAR);
    }
    
    public static String translate(String string, char c) {
        return ChatColor.translateAlternateColorCodes(c, string);
    }
}
