/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.server;

/**
 *
 * @author Aleksander
 */
public class Server {
    private final String id, name;
    
    public Server(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public String getID() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
}
