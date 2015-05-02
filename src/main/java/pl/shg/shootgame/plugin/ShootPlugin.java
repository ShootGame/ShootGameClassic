/*
 * Copyright (C) 2014 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.shootgame.plugin;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import pl.shg.shootgame.api.command.BukkitCommandExecutor;
import pl.shg.shootgame.api.command.CommandManager;
import pl.shg.shootgame.api.server.Servers;
import pl.shg.shootgame.listeners.PlayerListeners;

/**
 *
 * @author Aleksander
 */
public class ShootPlugin extends JavaPlugin {
    private static FileConfiguration configuration;
    
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        configuration = this.getConfig();
        
        CommandManager.registerDefaults();
        this.registerBukkitCommands();
        this.registerListeners();
        this.registerServers();
    }
    
    private void registerBukkitCommands() {
        CommandExecutor executor = new BukkitCommandExecutor();
        this.getCommand("server").setExecutor(executor);
        this.getCommand("servers").setExecutor(executor);
        this.getCommand("shg").setExecutor(executor);
        this.getCommand("staff").setExecutor(executor);
        this.getCommand("stats").setExecutor(executor);
        this.getCommand("xp").setExecutor(executor);
    }
    
    private void registerListeners() {
        this.getServer().getPluginManager().registerEvents(new PlayerListeners(), this);
    }
    
    private void registerServers() {
        try {
            String url = this.getConfig().getString("server-list");
            FileConfiguration target = YamlConfiguration.loadConfiguration(new URL(url).openStream());
            Servers.setConfiguration(target);
            
            new ServersLoader(this.getConfig().getString("server", "Development").toLowerCase(), target).initialize();
            Servers.setPingInterval(target.getLong("ping-interval", 5 * 20L));
            long interval = Servers.getPingInterval();
            this.getServer().getScheduler().runTaskTimerAsynchronously(this, new ServerPingTask(), interval, interval);
        } catch (IOException ex) {
            Logger.getLogger(ShootPlugin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static FileConfiguration getConfiguration() {
        return configuration;
    }
}
