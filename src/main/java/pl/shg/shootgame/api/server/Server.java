/*
 * Copyright (C) 2014 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.shootgame.api.server;

import pl.shg.shootgame.api.util.Validate;

/**
 *
 * @author Aleksander
 */
public class Server {
    private final ServerInfo info;
    
    public Server(ServerInfo info) {
        Validate.notNull(info, "info can not be null");
        this.info = info;
    }
    
    public ServerInfo getInfo() {
        return this.info;
    }
    
    public boolean isCurrent() {
        return false;
    }
}
