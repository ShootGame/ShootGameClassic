/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.shootgame.api.util;

/**
 *
 * @author Aleksander
 */
public class ArcadeData {
    public static final char SEPARATOR = '$';
    
    public static Object[] fromData(String data) {
        String[] values = data.split(String.valueOf(SEPARATOR));
        Object[] objects = new Object[4];
        
        for (int i = 0; i < values.length; i++) {
            try {
                objects[i] = readData(i, data);
            } catch (NumberFormatException ex) {
                
            }
        }
        return objects;
    }
    
    public static String toData(Object[] data) {
        StringBuilder builder = new StringBuilder();
        for (Object obj : data) {
            builder.append(obj.toString()).append(SEPARATOR);
        }
        String result = builder.toString();
        return result.substring(0, result.length() - 1);
    }
    
    private static Object readData(int i, String value) throws NumberFormatException {
        switch (i) {
            case 0: return value; // map name
            case 1: return Integer.parseInt(value); // match status
            case 2: return Integer.parseInt(value); // players in match
            case 3: return Integer.parseInt(value); // slots in match
            default: return null;
        }
    }
}
