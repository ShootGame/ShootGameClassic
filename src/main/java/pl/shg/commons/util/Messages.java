/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.util;

import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.PacketPlayOutChat;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R1.util.CraftChatMessage;
import org.bukkit.entity.Player;

/**
 *
 * @author Aleksander
 */
public class Messages {
    public static final char CODE = '`';
    
    public static void sendAction(Player player, String message) {
        NMSHacks.sendPacket(player, new PacketPlayOutChat(NMSHacks.toJSONText(message), (byte) 2)); // action bar     
    }
    
    public static void sendChat(Player player, String message) {
        sendChatPacket(player, message, (byte) 0); // classic hidable message
    }
    
    public static void sendMessage(Player player, String message) {
        sendChatPacket(player, message, (byte) 1); // fake command message
    }
    
    public static String translate(String string) {
        return translate(string, Messages.CODE);
    }
    
    public static String translate(String string, char code) {
        return ChatColor.translateAlternateColorCodes(code, string);
    }
    
    private static void sendChatPacket(Player player, String message, byte type) {
        for (IChatBaseComponent component : CraftChatMessage.fromString(message)) {
            NMSHacks.sendPacket(player, new PacketPlayOutChat(component, type));
        }
    }
}
