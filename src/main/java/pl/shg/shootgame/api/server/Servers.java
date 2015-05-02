/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.shootgame.api.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

/**
 *
 * @author Aleksander
 */
public class Servers {
    private static FileConfiguration configuration;
    private static OnlineServer online;
    private static long pingInterval;
    private static final List<TargetServer> servers = new ArrayList<>();
    
    public static void addServer(TargetServer server) {
        servers.add(server);
    }
    
    public static TargetServer findServer(String query) {
        for (TargetServer server : servers) {
            if (server.getName().toLowerCase().contains(query.toLowerCase())) {
                return server;
            }
        }
        return null;
    }
    
    public static FileConfiguration getConfiguration() {
        return configuration;
    }
    
    public static OnlineServer getOnline() {
        return online;
    }
    
    public static long getPingInterval() {
        return pingInterval;
    }
    
    public static TargetServer getServer(String id) {
        for (TargetServer server : servers) {
            if (server.getName().equalsIgnoreCase(id)) {
                return server;
            }
        }
        return null;
    }
    
    public static List<TargetServer> getServers() {
        return servers;
    }
    
    public static void setConfiguration(FileConfiguration configuration) {
        if (Servers.configuration != null) {
            throw new UnsupportedOperationException("Cannot redefine singleton configuration");
        } else {
            Servers.configuration = configuration;
        }
    }
    
    public static void setOnline(OnlineServer online) {
        if (Servers.online != null) {
            throw new UnsupportedOperationException("Cannot redefine singleton online");
        } else {
            Bukkit.getLogger().log(Level.INFO, "Serwer jest uruchamiany jako {0}...", online.getID());
            Servers.online = online;
        }
    }
    
    public static void setPingInterval(long interval) {
        Servers.pingInterval = interval;
    }
}
