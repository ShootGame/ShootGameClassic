/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.server;

import java.util.List;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 *
 * @author Aleksander
 */
public class ServersPingedEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    
    public ServersPingedEvent() {
        super(true);
    }
    
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    
    public List<TargetServer> getServers() {
        return Servers.getServers();
    }
    
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
