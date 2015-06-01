/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.server;

/**
 *
 * @author Aleksander
 */
public class ArcadeData {
    public static final char SEPARATOR = '\u0040'; // @
    
    public static Object[] fromData(String data) {
        String[] values = data.split("\\" + String.valueOf(SEPARATOR));
        Object[] objects = new Object[values.length];
        
        for (byte i = 0; i < values.length; i++) {
            try {
                objects[i] = readData(i, values[i]);
            } catch (NumberFormatException ex) {
                
            }
        }
        return objects;
    }
    
    public static String toData(Object[] data) {
        StringBuilder builder = new StringBuilder();
        for (Object obj : data) {
            builder.append(obj.toString()).append(String.valueOf(SEPARATOR));
        }
        String result = builder.toString();
        return result.substring(0, result.length() - 1);
    }
    
    private static Object readData(byte i, String value) throws NumberFormatException {
        switch (i) {
            // current maps display name
            case 0: return value;
            // match status: 0 - restarting, 1 - starting, 2 - running, 3 - cycling
            case 1: return Integer.parseInt(value);
            // amount of players in current teams
            case 2: return Integer.parseInt(value);
            // totals slots in the teams
            case 3: return Integer.parseInt(value);
            // return if null
            default: return null;
        }
    }
}
