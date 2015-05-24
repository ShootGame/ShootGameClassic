/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.settings;

/**
 *
 * @author Aleksander
 */
public interface IDefinition {
    Object get();
    
    boolean set(Object obj);
    
    int value();
}
