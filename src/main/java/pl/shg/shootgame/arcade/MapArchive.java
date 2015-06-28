/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.shootgame.arcade;

import java.io.File;
import java.util.logging.Level;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import pl.shg.arcade.api.location.WorldManager;
import pl.shg.arcade.bukkit.plugin.ArcadeShutdownEvent;
import pl.shg.commons.time.UnixTime;
import pl.shg.shootgame.plugin.ShootPlugin;

/**
 *
 * @author Aleksander
 */
public class MapArchive implements Listener {
    public MapArchive() {
        this.getPlugins().registerEvents(this, ShootPlugin.getPlugin());
    }
    
    @EventHandler
    public void onArcadeShutdown(ArcadeShutdownEvent e) {
        File directory = WorldManager.DIRECTORY;
        
        UnixTime time = UnixTime.now();
        int i = 0;
        
        this.log("Usuwanie archiwum swiatow map Arcade...");
        for (File world : directory.listFiles()) {
            if (world.exists() && world.isDirectory() && !world.isHidden()) {
                world.delete();
                i++;
            }
        }
        
        this.log("Usunieto " + i + " swiatow map Arcade w " + (UnixTime.now().getTime() - time.getTime()) + " ms.");
    }
    
    private PluginManager getPlugins() {
        return ShootPlugin.getPlugin().getServer().getPluginManager();
    }
    
    private void log(String message) {
        ShootPlugin.getPlugin().getLogger().log(Level.INFO, message);
    }
}
