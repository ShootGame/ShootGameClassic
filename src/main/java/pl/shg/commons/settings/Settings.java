/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.settings;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aleksander
 */
public class Settings {
    // default, global settings
    public static final Setting SOUNDS = new SoundSetting();
    
    private static final List<Setting> settings = new ArrayList<>();
    
    public static Setting byName(String name) {
        for (Setting setting : getSettings()) {
            if (setting.getName().equals(name.toLowerCase())) {
                return setting;
            }
        }
        return null;
    }
    
    public static List<Setting> getSettings() {
        return settings;
    }
    
    public static Setting match(String match) {
        for (Setting setting : getSettings()) {
            if (setting.getName().contains(match.toLowerCase())) {
                return setting;
            }
        }
        return null;
    }
    
    public static void register(Setting setting) {
        settings.add(setting);
    }
}
