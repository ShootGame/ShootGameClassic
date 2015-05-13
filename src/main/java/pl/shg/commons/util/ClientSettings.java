/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.util;

import net.minecraft.server.v1_8_R1.EntityPlayer;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 *
 * @author Aleksander
 */
public class ClientSettings {
    private final CraftPlayer craft;
    private final EntityPlayer nms;
    
    public ClientSettings(Player player) {
        this.craft = (CraftPlayer) player;
        this.nms = this.craft.getHandle();
    }
    
    public CraftPlayer getCraft() {
        return this.craft;
    }
    
    public ChatStatus getChat() {
        switch (this.nms.getChatFlags()) { // returns "bV" variable
            case FULL: return ChatStatus.ENABLED;
            case HIDDEN: return ChatStatus.HIDDEN;
            case SYSTEM: return ChatStatus.COMMANDS_ONLY;
            default: throw new UnsupportedOperationException("Not supported yet.");
        }
    }
    
    public String getLocale() {
        return this.nms.locale;
    }
    
    public EntityPlayer getNMS() {
        return this.nms;
    }
    
    public static ClientSettings newInstance(Player player) {
        return new ClientSettings(player);
    }
}
