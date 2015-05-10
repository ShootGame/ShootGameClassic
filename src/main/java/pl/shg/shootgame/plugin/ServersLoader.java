/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.shootgame.plugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import pl.shg.commons.server.ArcadeTarget;
import pl.shg.commons.server.LobbyTarget;
import pl.shg.commons.server.MinecraftTarget;
import pl.shg.commons.server.OnlineServer;
import pl.shg.commons.server.Servers;
import pl.shg.commons.server.TargetServer;

/**
 *
 * @author Aleksander
 */
public class ServersLoader {
    private final String current;
    private final FileConfiguration file;
    
    public ServersLoader(String current, FileConfiguration file) {
        this.current = current;
        this.file = file;
    }
    
    public FileConfiguration getFile() {
        return this.file;
    }
    
    public void initialize() {
        this.loadArcade();
        this.loadLobby();
        this.loadMinecraft();
    }
    
    private void addServer(TargetServer server) {
        if (this.current.equals(server.getID())) {
            Servers.setOnline(new OnlineServer(server.getID(), server.getName()));
        } else {
            Servers.addServer(server);
        }
    }
    
    private void loadArcade() {
        Bukkit.getLogger().info("Ladowanie serwerow (1/3)...");
        if (!this.getFile().isSet("arcade")) {
            return;
        }
        
        for (String id : this.getFile().getConfigurationSection("arcade").getKeys(false)) {
            this.addServer(new ArcadeTarget(
                    this.getFile().getString("arcade." + id + ".address", "localhost:" + TargetServer.PORT),
                    id,
                    this.getFile().getString("arcade." + id + ".name", "Serwer Arcade"),
                    this.getFile().getBoolean("arcade." + id + ".public", false)
            ));
        }
    }
    
    private void loadLobby() {
        Bukkit.getLogger().info("Ladowanie serwerow (2/3)...");
        if (!this.getFile().isSet("lobby")) {
            return;
        }
        
        for (String id : this.getFile().getConfigurationSection("lobby").getKeys(false)) {
            this.addServer(new LobbyTarget(
                    this.getFile().getString("lobby." + id + ".address", "localhost:" + TargetServer.PORT),
                    id,
                    this.getFile().getString("lobby." + id + ".name", "Serwer Lobby")
            ));
        }
    }
    
    private void loadMinecraft() {
        Bukkit.getLogger().info("Ladowanie serwerow (3/3)...");
        if (!this.getFile().isSet("minecraft")) {
            return;
        }
        
        for (String id : this.getFile().getConfigurationSection("minecraft").getKeys(false)) {
            this.addServer(new MinecraftTarget(
                    this.getFile().getString("minecraft." + id + ".address", "localhost:" + TargetServer.PORT),
                    Material.getMaterial(this.getFile().getInt("minecraft." + id + ".icon", 2)),
                    id,
                    this.getFile().getString("minecraft." + id + ".name", "Serwer Minecraft"),
                    this.getFile().getBoolean("minecraft." + id + ".public", false)
            ));
        }
    }
}
