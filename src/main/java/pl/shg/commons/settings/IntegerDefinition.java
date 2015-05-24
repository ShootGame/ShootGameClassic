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
public class IntegerDefinition extends PlayerSettingDefinition {
    private int value;
    
    public IntegerDefinition(int def) {
        super(def);
    }
    
    @Override
    public Object get() {
        return this.value;
    }
    
    @Override
    public boolean set(Object obj) {
        if (obj instanceof Integer) {
            this.value = (Integer) obj;
        }
        return false;
    }
    
    @Override
    public int value() {
        return this.value;
    }
}
