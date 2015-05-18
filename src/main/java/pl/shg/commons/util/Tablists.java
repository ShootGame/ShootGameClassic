/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.util;

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
    public static final int PING = 1000;
    
    public static void sendHeaderFooter(Player player, String header, String footer) {
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
        packet.header = new BaseComponent[] {
            new TextComponent(header)
        };
        packet.footer = new BaseComponent[] {
            new TextComponent(footer)
        };
        NMSHacks.sendPacket(player, packet);
        
        /*for (int y = 0; y < 75; y++) { // 80 cells
            sendCells(player, new TabCell(0, 0, "cos", new GameProfile(UUID.randomUUID(), "cos"), 1000));
        }*/
    }
    
    public static void sendCells(Player player, TabCell... cells) {
        PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();
        packet.a = EnumPlayerInfoAction.ADD_PLAYER;
        for (TabCell cell : cells) {
            if (!cell.isUpdateWaiting()) {
                continue;
            }
            
            cell.setUpdateWaiting(false);
            packet.b.add(new PlayerInfoData(packet, cell.getProfile(), cell.getPing(),
                    EnumGamemode.NOT_SET, ChatSerializer.a(cell.getName())));
        }
        NMSHacks.sendPacket(player, packet);
    }
}
