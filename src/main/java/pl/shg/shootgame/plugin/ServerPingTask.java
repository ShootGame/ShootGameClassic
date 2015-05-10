/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.shootgame.plugin;

import org.bukkit.Bukkit;
import pl.shg.commons.server.Servers;
import pl.shg.commons.server.ServersPingedEvent;
import pl.shg.commons.server.TargetServer;

/**
 *
 * @author Aleksander
 */
public class ServerPingTask implements Runnable {
    @Override
    public void run() {
        for (TargetServer server : Servers.getServers()) {
            server.ping();
        }
        Bukkit.getPluginManager().callEvent(new ServersPingedEvent());
    }
}
