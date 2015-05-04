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
import net.minecraft.server.v1_8_R1.*;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R1.util.CraftChatMessage;
import org.bukkit.entity.Player;

/**
 *
 * @author Aleksander
 */
public class NMSHacks {
    private final Player player;
    
    public NMSHacks(Player player) {
        this.player = player;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public void sendActionMessage(String message) {
        sendPacket(this.getPlayer(), new PacketPlayOutChat(toJSONText(message), (byte) 2)); // action bar     
    }
    
    public void sendChatMessage(String message) {
        this.sendChatPacket(message, (byte) 0); // classic hidable message
    }
    
    public void sendMessage(String message) {
        this.sendChatPacket(message, (byte) 1); // fake command message
    }
    
    public void sendSubtitle(String subtitle) {
        this.sendSubtitle(subtitle, 3 * 20);
    }
    
    public void sendSubtitle(String subtitle, int stay) {
        this.sendSubtitle(subtitle, 10, stay, 10);
    }
    
    public void sendSubtitle(String subtitle, int in, int stay, int out) {
        sendPacket(this.getPlayer(), new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, toJSONText(subtitle), in, stay, out));
    }
    
    public void sendTitle(String title) {
        this.sendTitle(title, 3 * 20);
    }
    
    public void sendTitle(String title, int stay) {
        this.sendTitle(title, 10, stay, 10);
    }
    
    public void sendTitle(String title, int in, int stay, int out) {
        sendPacket(this.getPlayer(), new PacketPlayOutTitle(EnumTitleAction.TITLE, toJSONText(title), in, stay, out));
    }
    
    public void setTabList(String header, String footer) {
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter(toJSONText(header));
        try {
            Field field = packet.getClass().getDeclaredField("b");
            field.setAccessible(true);
            field.set(packet, toJSONText(footer));
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(NMSHacks.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            sendPacket(this.getPlayer(), packet);
        }
    }
    
    public synchronized void sendChatPacket(String message, byte type) {
        for (IChatBaseComponent component : CraftChatMessage.fromString(message)) {
            sendPacket(this.getPlayer(), new PacketPlayOutChat(component, type));
        }
    }
    
    public static synchronized void sendPacket(Player player, Packet packet) {
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }
    
    public static IChatBaseComponent toJSONText(String message) {
        IChatBaseComponent component = ChatSerializer.a("{\"text\": \"" + message + "\"}");
        return CraftChatMessage.fixComponent(component);
    }
    
    public static NMSHacks of(Player player) {
        return new NMSHacks(player);
    }
}
