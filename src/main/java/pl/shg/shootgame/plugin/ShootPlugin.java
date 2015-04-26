/*
 * Copyright (C) 2014 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.shootgame.plugin;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import pl.shg.shootgame.api.command.BukkitCommandExecutor;
import pl.shg.shootgame.api.command.CommandManager;
import pl.shg.shootgame.listeners.PlayerListeners;

/**
 *
 * @author Aleksander
 */
public class ShootPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        CommandManager.registerDefaults();
        this.registerBukkitCommands();
        this.registerListeners();
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
}
