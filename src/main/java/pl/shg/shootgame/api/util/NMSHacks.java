/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.shootgame.api.util;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.EnumTitleAction;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.Packet;
import net.minecraft.server.v1_8_R1.PacketPlayOutChat;
import net.minecraft.server.v1_8_R1.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R1.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R1.util.CraftChatMessage;
import org.bukkit.entity.Player;

/**
 *
 * @author Aleksander
 */
public class NMSHacks {
    public static void sendActionMessage(Player player, String message) {
        sendPacket(player, new PacketPlayOutChat(toJSONText(message), (byte) 2)); // action bar
                
    }
    
    public static void sendChatMessage(Player player, String message) {
        sendChatPacket(player, message, (byte) 0); // classic hidable message
    }
    
    public static void sendMessage(Player player, String message) {
        sendChatPacket(player, message, (byte) 1); // fake command message
    }
    
    public static void sendSubtitle(Player player, String subtitle) {
        sendPacket(player, new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, toJSONText(subtitle)));
    }
    
    public static void sendTitle(Player player, String title) {
        sendPacket(player, new PacketPlayOutTitle(EnumTitleAction.TITLE, toJSONText(title)));
    }
    
    public static void setTabList(Player player, String header, String footer) {
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter(toJSONText(header));
        try {
            Field field = packet.getClass().getDeclaredField("b");
            field.setAccessible(true);
            field.set(packet, toJSONText(footer));
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(NMSHacks.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            sendPacket(player, packet);
        }
    }
    
    public static synchronized void sendChatPacket(Player player, String message, byte type) {
        for (IChatBaseComponent component : CraftChatMessage.fromString(message)) {
            sendPacket(player, new PacketPlayOutChat(component, type));
        }
    }
    
    public static synchronized void sendPacket(Player player, Packet packet) {
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }
    
    public static IChatBaseComponent toJSONText(String message) {
        IChatBaseComponent component = ChatSerializer.a("{\"text\": \"" + message + "\"}");
        return CraftChatMessage.fixComponent(component);
    }
}
