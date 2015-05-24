/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.users;

import java.util.UUID;

/**
 *
 * @author Aleksander
 */
public class DBUser {
    private final UUID uuid;
    
    public DBUser(UUID uuid) {
        this.uuid = uuid;
    }
    
    public UUID getUUID() {
        return this.uuid;
    }
}
