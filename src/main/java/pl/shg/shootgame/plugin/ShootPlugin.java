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
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import pl.shg.commons.command.CommandBase;
import pl.shg.commons.server.BungeeCordProxy;
import pl.shg.commons.server.Servers;
import pl.shg.shootgame.listeners.PlayerListeners;

/**
 *
 * @author Aleksander
 */
public class ShootPlugin extends JavaPlugin {
    private static FileConfiguration configuration;
    private static Plugin instance;
    
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        configuration = this.getConfig();
        instance = this;
        Servers.setProxy(new BungeeCordProxy());
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        
        this.registerBukkitCommands();
        this.registerListeners();
        this.registerServers();
    }
    
    private void registerBukkitCommands() {
        CommandBase.registerDefaults(this);
        CommandBase.registerHelpTopic(this);
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
    
    public static Plugin getPlugin() {
        return instance;
    }
}
