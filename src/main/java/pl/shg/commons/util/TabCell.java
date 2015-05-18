/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.util;

import com.mojang.authlib.GameProfile;

/**
 *
 * @author Aleksander
 */
public class TabCell extends Cell {
    private String name;
    private final GameProfile profile;
    private final int ping;
    private boolean update;
    
    public TabCell(int column, int line, String name, GameProfile profile) {
        super(column, line);
        this.name = name;
        this.profile = profile;
        this.ping = Tablists.PING;
        this.update = true;
    }
    
    public TabCell(int column, int line, String name, GameProfile profile, int ping) {
        super(column, line);
        this.name = name;
        this.profile = profile;
        this.ping = ping;
        this.update = true;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TabCell) {
            return ((TabCell) obj).getColumn() == this.getColumn()
                    && ((TabCell) obj).getLine() == this.getLine();
        }
        return false;
    }
    
    public int getColumn() {
        return this.getX();
    }
    
    public int getLine() {
        return this.getY();
    }
    
    public String getName() {
        return this.name;
    }
    
    public GameProfile getProfile() {
        return this.profile;
    }
    
    public int getPing() {
        return this.ping;
    }
    
    public boolean isUpdateWaiting() {
        return this.update;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setUpdateWaiting(boolean update) {
        this.update = update;
    }
}
