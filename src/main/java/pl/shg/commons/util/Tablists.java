/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.util;

import com.mojang.authlib.GameProfile;
import java.util.Arrays;
import java.util.UUID;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.EnumGamemode;
import net.minecraft.server.v1_8_R1.EnumPlayerInfoAction;
import net.minecraft.server.v1_8_R1.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R1.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R1.PlayerInfoData;
import org.bukkit.entity.Player;

/**
 *
 * @author Aleksander
 */
public class Tablists {
    public static final int PING = 1100;
    // zostawiam to w pizduu i robie swoje api
    
    public static void sendHeaderFooter(Player player, String header, String footer) {
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
        packet.header = new BaseComponent[] {
            new TextComponent(header)
        };
        packet.footer = new BaseComponent[] {
            new TextComponent(footer)
        };
        NMSHacks.sendPacket(player, packet);
    }
    
    /*public static void sendCells(Player player) {
        PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();
        packet.a = EnumPlayerInfoAction.ADD_PLAYER;
        for (TabCell cell : TabCell.getCells()) {
            if (!cell.isUpdateWaiting()) {
                continue;
            }
            
            cell.setUpdateWaiting(false);
            String prefix = "";
            String suffix = "";
            if (cell.getDisplayName().length() > 15) { // max 16 znakow w prefixie i suffixie
                prefix = cell.getDisplayName().substring(0, 16);
                suffix = cell.getDisplayName().substring(16);
            } else {
                prefix = cell.getDisplayName();
            }
        }
        NMSHacks.sendPacket(player, packet);
    }*/
    
    public static void sendClearCells(Player player) { // najpierw to wysyla sie
        PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();
        for (int i = 0; i < 79; i++) { // 80 cells
            String name = Colors.random(7);
            System.out.println(name);
            PlayerInfoData info = new PlayerInfoData(packet, new GameProfile(UUID.randomUUID(), ""),
                    Tablists.PING, EnumGamemode.NOT_SET, ChatSerializer.a(name));
            packet.b.add(info);
            TabCell.add(new TabCell(i, name, info.a(), player));
        }
        NMSHacks.sendPacket(player, packet);
    }
    
    public static void setName(int i, String name) {
        TabCell cell = TabCell.getCells().get(0);
        cell.setDisplayName(name);
        updateCell(cell);
    }
    
    public static void updateCell(TabCell cell) {
        if (cell.getDisplayName().length() > 16) { // max 16 znakow w prefixie i suffixie
            Tags.setPrefix(cell.getName(), Arrays.asList(cell.getPlayer()), cell.getDisplayName().substring(0, 16));
            Tags.setSuffix(cell.getName(), Arrays.asList(cell.getPlayer()), cell.getDisplayName().substring(16));
        } else {
            Tags.setPrefix(cell.getName(), Arrays.asList(cell.getPlayer()), cell.getDisplayName());
        }
    }
}
