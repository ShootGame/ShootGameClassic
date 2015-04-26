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
public class ServerBuilder {
    protected boolean accessible;
    protected String name;
    
    public ServerBuilder() {}
    
    public ServerBuilder accessible(boolean accessible) {
        this.accessible = accessible;
        return this;
    }
    
    public ServerBuilder name(String name) {
        Validate.notNull(name, "name can not be null");
        this.name = name;
        return this;
    }
}
