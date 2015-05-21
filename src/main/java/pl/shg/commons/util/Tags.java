/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.util;

import java.util.Arrays;
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
    
    public static void register(Player player) {
        ScoreboardTeam team = new ScoreboardTeam(new Scoreboard(), player.getName());
        PacketPlayOutScoreboardTeam packet = new PacketPlayOutScoreboardTeam(team, 0);
        packet.g = Arrays.asList(player.getName()); // add specifited players to the team
        tags.put(player.getName(), team);
        NMSHacks.sendPacket(player, packet);
    }
    
    public static void unregister(Player player) {
        tags.remove(player.getName());
    }
    
    public static void setPrefix(Player player, Collection<? extends Player> showPlayers, String prefix) {
        set(player, showPlayers, prefix, null);
    }
    
    public static void setPrefix(Player player, String prefix) {
        set(player, Bukkit.getOnlinePlayers(), prefix, null);
    }
    
    public static void setSuffix(Player player, Collection<? extends Player> showPlayers, String suffix) {
        set(player, showPlayers, null, suffix);
    }
    
    public static void setSuffix(Player player, String suffix) {
        set(player, Bukkit.getOnlinePlayers(), null, suffix);
    }
     
    public static void set(Player player, Collection<? extends Player> showPlayers, String prefix, String suffix) {
        ScoreboardTeam team = tags.get(player.getName());
        Validate.notNull(team, "Team can not be null");
        
        if (prefix != null) {
            team.setPrefix(prefix);
        }
        if (suffix != null) {
            team.setSuffix(suffix);
        }
        
        PacketPlayOutScoreboardTeam packet = new PacketPlayOutScoreboardTeam(team, 2);
        for (Player players : showPlayers) {
            NMSHacks.sendPacket(players, packet);
        }
        NMSHacks.sendPacket(player, packet);
    }
}
