/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.util;

import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

/**
 *
 * @author Aleksander
 */
public class Fireworks {
    public static final int POWER = 3;
    
    public static Firework create(Location location) {
        return create(location, null);
    }
    
    public static Firework create(Location location, FireworkEffect effect) {
        return create(location, effect, Fireworks.POWER);
    }
    
    public static Firework create(Location location, FireworkEffect effect, int power) {
        Firework firework = location.getWorld().spawn(location, Firework.class);
        FireworkMeta meta = firework.getFireworkMeta();
        if (effect != null) {
            meta.addEffect(effect);
        }
        meta.setPower(power);
        firework.setFireworkMeta(meta);
        return firework;
    }
}
