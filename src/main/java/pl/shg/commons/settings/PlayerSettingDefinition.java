/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.settings;

import org.bukkit.entity.Player;

/**
 *
 * @author Aleksander
 */
public abstract class PlayerSettingDefinition implements IDefinition {
    private final Object def;
    private final Player player;
    
    public PlayerSettingDefinition(Object def) {
        this(def, null);
    }
    
    public PlayerSettingDefinition(Object def, Player player) {
        this.def = def;
        this.player = player;
    }
    
    public Object getDefaultValue() {
        return this.def;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public boolean isPlayerSet() {
        return this.player != null;
    }
}
