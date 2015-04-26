/*
 * Copyright (C) 2014 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.shootgame.api;

import pl.shg.shootgame.api.util.Validate;

/**
 *
 * @author Aleksander
 */
public class Color {
    public static final char SECTION_SIGN = '§';
    
    public static final String BLACK = SECTION_SIGN + "0";
    public static final String DARK_BLUE = SECTION_SIGN + "1";
    public static final String DARK_GREEN = SECTION_SIGN + "2";
    public static final String DARK_AQUA = SECTION_SIGN + "3";
    public static final String DARK_RED = SECTION_SIGN + "4";
    public static final String DARK_PURPLE = SECTION_SIGN + "5";
    public static final String GOLD = SECTION_SIGN + "6";
    public static final String GRAY = SECTION_SIGN + "7";
    public static final String DARK_GRAY = SECTION_SIGN + "8";
    public static final String BLUE = SECTION_SIGN + "9";
    public static final String GREEN = SECTION_SIGN + "A";
    public static final String AQUA = SECTION_SIGN + "B";
    public static final String RED = SECTION_SIGN + "C";
    public static final String LIGHT_PURPLE = SECTION_SIGN + "D";
    public static final String YELLOW = SECTION_SIGN + "E";
    public static final String WHITE = SECTION_SIGN + "F";
    
    public static final String OBFUSCATED = SECTION_SIGN + "K";
    public static final String BOLD = SECTION_SIGN + "L";
    public static final String STRIKETHROUGH = SECTION_SIGN + "M";
    public static final String UNDERLINE = SECTION_SIGN + "N";
    public static final String ITALIC = SECTION_SIGN + "O";
    public static final String RESET = SECTION_SIGN + "R";
    
    public static String translate(String message) {
        Validate.notNull(message, "message can not be null");
        return Color.translate(message, '`');
    }
    
    public static String translate(String message, char code) {
        Validate.notNull(message, "message can not be null");
        Validate.notNull(code, "code can not be null");
        return message.replaceAll(String.valueOf(code), String.valueOf(Color.SECTION_SIGN));
    }
}
