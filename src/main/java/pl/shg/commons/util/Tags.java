/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */

package pl.shg.commons.util;

import java.util.Arrays;
import java.util.Collection;
import net.minecraft.server.v1_8_R1.PacketPlayOutScoreboardTeam;
import net.minecraft.server.v1_8_R1.Scoreboard;
import net.minecraft.server.v1_8_R1.ScoreboardTeam;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 *
 * @author Filip
 */
public class Tags {
    public static void setPrefix(Player player, Collection<?> showPlayers, String prefix) {
        set(player, showPlayers, prefix, null);
    }
    
    public static void setPrefix(Player player, String prefix) {
        set(player, Bukkit.getOnlinePlayers(), prefix, null);
    }
    
    public static void setSuffix(Player player, Collection<?> showPlayers, String suffix) {
        set(player, showPlayers, null, suffix);
    }
    
    public static void setSuffix(Player player, String suffix) {
        set(player, Bukkit.getOnlinePlayers(), null, suffix);
    }
     
    public static void set(Player player, Collection<?> showPlayers, String prefix, String suffix) {
        ScoreboardTeam team = new ScoreboardTeam(new Scoreboard(), player.getName());
        if (prefix != null) {
            team.setPrefix(prefix);
        }
        if (suffix != null) {
            team.setSuffix(suffix);
        }
        PacketPlayOutScoreboardTeam packet = new PacketPlayOutScoreboardTeam(team, 0);
        packet.g = Arrays.asList(player.getName()); // add specifited players to the team
        for (Object players : showPlayers) {
            NMSHacks.sendPacket((Player) players, packet);
        }
        NMSHacks.sendPacket(player, packet);
    }
}
