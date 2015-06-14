/*
 * Copyright (C) 2014 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.shootgame.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.shg.shootgame.Language;
import pl.shg.shootgame.api.Log;

/**
 *
 * @author Aleksander
 */
public class PlayerListeners implements Listener {
    @EventHandler
    public void onAsyncPlayerPreLogin(AsyncPlayerPreLoginEvent e) {
        // TODO Update table "dev_players", row "name" to the current players name
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        
        this.staff(Language.LISTENERS_JOIN, e.getPlayer().getDisplayName() + ChatColor.YELLOW);
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
        
        this.staff(Language.LISTENERS_QUIT, e.getPlayer().getDisplayName() + ChatColor.YELLOW);
    }
    
    private void staff(Language message, Object... params) {
        String prefix = ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + "INFO" + ChatColor.DARK_AQUA + "] " + ChatColor.YELLOW;
        
        for (Player online : Bukkit.getOnlinePlayers()) {
            if (online.hasPermission("shootgame.staff")) {
                online.sendMessage(prefix + message.get(online, params));
            }
        }
    }
    
    private String translateDeathMessage(String message, String player, String killer) {
        if (message.contains("fell out"))
            message = player + ChatColor.GRAY + " wypadl ze swiata";
        else if (message.contains("hit the ground") || message.contains("fell from a high place"))
            message = player + ChatColor.GRAY + " spadl z wysokosci";
        else if (message.contains("drowned"))
            message = player + ChatColor.GRAY + " utopil sie";
        else if (message.contains("swim in lava"))
            message = player + ChatColor.GRAY + " spalil sie w lawie";
        else if (message.contains("struck by lightning"))
            message = player + ChatColor.GRAY + " zabil piorun";
        else if (message.contains("blew up") || message.contains("blown up"))
            message = player + ChatColor.GRAY + " wybuchl";
        else if (message.contains("went up in flames") || message.contains("burned to death"))
            message = player + ChatColor.GRAY + " spalil sie";
        else if (message.contains("pricked to death") || message.contains("walked into a cactus"))
            message = player + ChatColor.GRAY + " wskoczyl na kaktus";
        else if (message.contains("starved"))
            message = player + ChatColor.GRAY + " zmarl z glodu";
        else if (message.contains("was slain by magic"))
            message = player + ChatColor.GRAY + " zmarl magia";
        else if (message.contains("slain"))
            message = player + ChatColor.GRAY + " zostal zabity";
        else if (message.contains("shot"))
            message = player + ChatColor.GRAY + " zostal zastrzelony";
        else if (message.contains("died"))
            message = player + ChatColor.GRAY + " zmarl";
        
        if(killer != null)
            message = message + " przez " + killer;
        return message;
    }
}
