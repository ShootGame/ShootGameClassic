/*
 * Copyright (C) 2014 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.shootgame.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.shg.shootgame.api.Color;
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
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        
        Log.admins(e.getPlayer().getName() + " dolaczyl na serwer");
    }
    
    @EventHandler
    public void onPlayerKick(PlayerKickEvent e) {
        String reason = e.getLeaveMessage();
        if (reason == null) {
            reason = "wyrzucenie";
        }
        e.setLeaveMessage(null);
        
        Log.admins(e.getPlayer().getName() + " wyszedl z serwera (" + reason + ")");
    }
    
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
        
        Log.admins(e.getPlayer().getName() + " wyszedl z serwera (wyjscie)");
    }
    
    private String translateDeathMessage(String message, String player, String killer) {
        if (message.contains("fell out"))
            message = player + Color.GRAY + " wypadl ze swiata";
        else if (message.contains("hit the ground") || message.contains("fell from a high place"))
            message = player + Color.GRAY + " spadl z wysokosci";
        else if (message.contains("drowned"))
            message = player + Color.GRAY + " utopil sie";
        else if (message.contains("swim in lava"))
            message = player + Color.GRAY + " spalil sie w lawie";
        else if (message.contains("struck by lightning"))
            message = player + Color.GRAY + " zabil piorun";
        else if (message.contains("blew up") || message.contains("blown up"))
            message = player + Color.GRAY + " wybuchl";
        else if (message.contains("went up in flames") || message.contains("burned to death"))
            message = player + Color.GRAY + " spalil sie";
        else if (message.contains("pricked to death") || message.contains("walked into a cactus"))
            message = player + Color.GRAY + " wskoczyl na kaktus";
        else if (message.contains("starved"))
            message = player + Color.GRAY + " zmarl z glodu";
        else if (message.contains("was slain by magic"))
            message = player + Color.GRAY + " zmarl magia";
        else if (message.contains("slain"))
            message = player + Color.GRAY + " zostal zabity";
        else if (message.contains("shot"))
            message = player + Color.GRAY + " zostal zastrzelony";
        else if (message.contains("died"))
            message = player + Color.GRAY + " zmarl";
        
        if(killer != null)
            message = message + " przez " + killer;
        return message;
    }
}
