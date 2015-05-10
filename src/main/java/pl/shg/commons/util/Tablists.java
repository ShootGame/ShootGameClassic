/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.util;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.server.v1_8_R1.PacketPlayOutPlayerListHeaderFooter;
import org.bukkit.entity.Player;

/**
 *
 * @author Aleksander
 */
public class Tablists {
    public static void sendHeaderFooter(Player player, String header, String footer) {
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter(NMSHacks.toJSONText(header));
        try {
            Field field = packet.getClass().getDeclaredField("b");
            field.setAccessible(true);
            field.set(packet, NMSHacks.toJSONText(footer));
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(NMSHacks.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            NMSHacks.sendPacket(player, packet);
        }
    }
}
