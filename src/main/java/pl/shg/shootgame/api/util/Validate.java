/*
 * Copyright (C) 2014 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.shootgame.api.util;

/**
 *
 * @author Aleksander
 */
public class Validate {
    public static void notNegative(double d, String message) {
        if (d < 0.0) {
            throw new IllegalArgumentException(message);
        }
    }
    
    public static void notNegative(int i, String message) {
        if (i < 0) {
            throw new IllegalArgumentException(message);
        }
    }
    
    public static void notNegative(long l, String message) {
        if (l < 0) {
            throw new IllegalArgumentException(message);
        }
    }
    
    public static void notNull(Object o, String message) {
        if (o == null) {
            throw new IllegalArgumentException(message);
        }
    }
    
    public static void notZero(double d, String message) {
        if (d == 0.0) {
            throw new IllegalArgumentException(message);
        }
    }
    
    public static void notZero(int i, String message) {
        if (i == 0) {
            throw new IllegalArgumentException(message);
        }
    }
    
    public static void notZero(long l, String message) {
        if (l == 0) {
            throw new IllegalArgumentException(message);
        }
    }
}
