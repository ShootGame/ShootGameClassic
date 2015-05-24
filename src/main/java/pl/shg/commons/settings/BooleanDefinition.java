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
public class BooleanDefinition extends PlayerSettingDefinition {
    private boolean value = false;
    
    public BooleanDefinition(boolean def) {
        super(def);
    }
    
    @Override
    public Object get() {
        return this.value;
    }
    
    @Override
    public boolean set(Object obj) {
        if (obj instanceof Boolean) {
            this.value = (Boolean) obj;
            return true;
        }
        return false;
    }
    
    @Override
    public int value() {
        if (this.value) {
            return 1; // true
        } else {
            return 0; // false
        }
    }
}
