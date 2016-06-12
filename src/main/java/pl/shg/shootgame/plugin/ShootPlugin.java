/*
 * Copyright (C) 2014 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.shootgame.plugin;

import com.mongodb.Function;
import com.mongodb.async.SingleResultCallback;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import pl.shg.commons.bukkit.BukkitCommons;
import pl.shg.commons.command.CommandBase;
import pl.shg.commons.mongo.Database;
import pl.shg.commons.mongo.Mongo;
import pl.shg.commons.mongo.ServersBase;
import pl.shg.commons.server.BungeeCordProxy;
import pl.shg.commons.server.Servers;
import pl.shg.shootgame.arcade.MapArchive;
import pl.shg.shootgame.arcade.PrimitiveAntiLogout;
import pl.shg.shootgame.command.ServerCommand;
import pl.shg.shootgame.command.ServersCommand;
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
        Servers.setProxy(new BungeeCordProxy(this));
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        
        BukkitCommons.initialize(this, new File(this.getConfig().getString("translations", "./lang")));
        
        this.registerBukkitCommands();
        this.registerListeners();
        this.registerServers();
        
//        new MapArchive();
        new PrimitiveAntiLogout();
        
        String uri = this.getConfig().getString("mongo.uri", Mongo.URI);
        String database = this.getConfig().getString("mongo.database", Mongo.DATABASE);
        
        this.getLogger().log(Level.INFO, "Laczenie z baza {0} poprzez {1}...", new Object[]{database, uri});
//        Database.initializeMongo(uri, database);
    }
    
    private void registerBukkitCommands() {
        CommandBase.register(this, new ServerCommand());
        CommandBase.register(this, new ServersCommand());
        
        CommandBase.registerHelpTopic(this);
    }
    
    private void registerListeners() {
        this.getServer().getPluginManager().registerEvents(new PlayerListeners(), this);
    }
    
    private void registerServers() {
        /* zbedne...
        
        String uri = this.getConfig().getString("servers.uri", ServersBase.URI);
        String database = this.getConfig().getString("servers.database", ServersBase.DATABASE);
        String collection = this.getConfig().getString("servers.collection", ServersBase.COLLECTION);
        
        this.getLogger().log(Level.INFO, "Laczenie z baza {0} poprzez {1}...", new Object[]{database, uri});
        Database.initializeServers(uri, database, collection);
        
        this.getLogger().log(Level.INFO, "Przeszukiwanie serwerow...");
        ServersBase.getServers().find().map(new Function<Document, String>() {
            @Override
            public String apply(Document document) {
                return document.getString("name");
            }
        }).into(new ArrayList<>(), new SingleResultCallback<List<String>>() {
            @Override
            public void onResult(final List<String> result, final Throwable throwable) {
                System.out.println("Names: " + result);
            }
        });
        */
        
        
        try {
            String url = this.getConfig().getString("server-list");
            FileConfiguration target = YamlConfiguration.loadConfiguration(new URL(url).openStream());
            Servers.setConfiguration(target);
            
            new ServersLoader(this.getConfig().getString("server-id", "Development").toLowerCase(), target).initialize();
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
