/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.server.v1_8_R1.PacketPlayOutScoreboardTeam;
import net.minecraft.server.v1_8_R1.Scoreboard;
import net.minecraft.server.v1_8_R1.ScoreboardTeam;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 *
 * @author Filip
 */
public class Tags {
    private static final Map<String, ScoreboardTeam> tags = new HashMap<>();
    
    public static void setPrefix(String player, Collection<? extends Player> showPlayers, String prefix) {
        set(player, showPlayers, prefix, null);
    }
    
    public static void setPrefix(String player, String prefix) {
        set(player, Bukkit.getOnlinePlayers(), prefix, null);
    }
    
    public static void setSuffix(String player, Collection<? extends Player> showPlayers, String suffix) {
        set(player, showPlayers, null, suffix);
    }
    
    public static void setSuffix(String player, String suffix) {
        set(player, Bukkit.getOnlinePlayers(), null, suffix);
    }
     
    public static void set(String player, Collection<? extends Player> showPlayers, String prefix, String suffix) {
        ScoreboardTeam team = new ScoreboardTeam(new Scoreboard(), player);
        
        if (prefix != null) {
            team.setPrefix(prefix);
        }
        if (suffix != null) {
            team.setSuffix(suffix);
        }
        PacketPlayOutScoreboardTeam packet1 = new PacketPlayOutScoreboardTeam(team, 0);
        PacketPlayOutScoreboardTeam packet = new PacketPlayOutScoreboardTeam(team, 2);
        for (Player players : showPlayers) {
            NMSHacks.sendPacket(players, packet1);
            NMSHacks.sendPacket(players, packet);
        }
    }
}
