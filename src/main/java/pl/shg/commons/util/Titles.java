/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.util;

import net.minecraft.server.v1_8_R1.EnumTitleAction;
import net.minecraft.server.v1_8_R1.PacketPlayOutTitle;
import org.bukkit.entity.Player;

/**
 *
 * @author Aleksander
 */
public class Titles {
    public static final int IN = 5;
    public static final int STAY = 60;
    public static final int OUT = 10;
    
    public static void send(Player player, String title, String subtitle) {
        send(player, title, subtitle, Titles.STAY);
    }
    
    public static void send(Player player, String title, String subtitle, int stay) {
        send(player, title, subtitle, Titles.IN, stay, Titles.OUT);
    }
    
    public static void send(Player player, String title, String subtitle, int in, int stay, int out) {
        if (title != null) {
            NMSHacks.sendPacket(player, new PacketPlayOutTitle(EnumTitleAction.TITLE,
                    NMSHacks.toJSONText(title), in, stay, out));
        }
        if (subtitle != null) {
            NMSHacks.sendPacket(player, new PacketPlayOutTitle(EnumTitleAction.SUBTITLE,
                    NMSHacks.toJSONText(subtitle), in, stay, out));
        }
    }
}
