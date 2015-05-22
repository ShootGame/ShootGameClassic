/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.util;

/**
 *
 * @author Aleksander
 */
public class Cell {
    private final int index;
    
    public Cell(int index) {
        this.index = index;
    }
    
    public int getIndex() {
        return this.index;
    }
}
