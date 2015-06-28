/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2015
 */
package pl.shg.shootgame.arcade;

import java.util.Arrays;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;
import pl.shg.shootgame.plugin.ShootPlugin;

/**
 * Primitive antilogout for handling a death after the player disconnects. Event
 * PlayerDeathEvent invokes all player-death events, so we can save statistic
 * in the database. The event is not invoked in the NMS server, so player
 * doesn't die - we must manually broadcast a death message.
 * @author Aleksander
 */
public class PrimitiveAntiLogout implements Listener {
    public PrimitiveAntiLogout() {
        this.getPlugins().registerEvents(this, ShootPlugin.getPlugin());
    }
    
    @EventHandler
    public void primitiveAntiLogout(PlayerQuitEvent e) {
        PlayerDeathEvent death = this.constructEvent(e.getPlayer());
        this.getPlugins().callEvent(death);
        
        // we must broadcast a death message, as the default player death
        if (death.getDeathMessage() != null && !death.getDeathMessage().endsWith(" died")) {
            ShootPlugin.getPlugin().getServer().broadcastMessage(death.getDeathMessage());
        }
    }
    
    private PlayerDeathEvent constructEvent(Player player) {
        return new PlayerDeathEvent(
                player,
                Arrays.asList(player.getInventory().getContents()),
                player.getExpToLevel(),
                0,
                0,
                0,
                null
        );
    }
    
    private PluginManager getPlugins() {
        return ShootPlugin.getPlugin().getServer().getPluginManager();
    }
}
