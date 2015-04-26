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
public class ServerInfo {
    private final ServerBuilder builder;
    
    public ServerInfo(ServerBuilder builder) {
        Validate.notNull(builder, "builder can not be null");
        this.builder = builder;
    }
    
    public boolean isAccessible() {
        return this.getBuilder().accessible;
    }
    
    public String getName() {
        return this.getBuilder().name;
    }
    
    private ServerBuilder getBuilder() {
        return this.builder;
    }
}
