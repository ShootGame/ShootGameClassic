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

/**
 *
 * @author Aleksander
 */
public class Servers {
    private static OnlineServer online;
    private static long pingInterval;
    private static final List<TargetServer> servers = new ArrayList<>();
    
    public static void addServer(TargetServer server) {
        servers.add(server);
    }
    
    public static OnlineServer getOnline() {
        return online;
    }
    
    public static long getPingInterval() {
        return pingInterval;
    }
    
    public static List<TargetServer> getServers() {
        return servers;
    }
    
    public static void setPingInterval(long interval) {
        Servers.pingInterval = interval;
    }
    
    public static void setOnline(OnlineServer online) {
        if (Servers.online != null) {
            throw new UnsupportedOperationException("Cannot redefine singleton online");
        } else {
            Bukkit.getLogger().log(Level.INFO, "Serwer jest uruchamiany jako {0}...", online.getID());
            Servers.online = online;
        }
    }
}
