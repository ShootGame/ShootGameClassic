/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.shootgame.api.server;

import org.bukkit.craftbukkit.libs.com.google.gson.Gson;

/**
 *
 * @author Aleksander
 */
public class ArcadeTarget extends TargetServer {
    private int arcadePlayers, arcadeSlots, status = 0;
    private String map;
    
    public ArcadeTarget(String address, String id, String name, boolean publicy) throws NumberFormatException {
        super(address, id, name, publicy);
    }
    
    @Override
    public void read(String data) {
        PingResponse response = new Gson().fromJson(data, PingResponse.class);
        this.setPlayers(response.getPlayers().getOnline());
        this.setSlots(response.getPlayers().getMax());
        
        String[] values = response.getDescription().split("$");
        for (int i = 0; i < values.length; i++) {
            try {
                this.setData(i, data);
            } catch (NumberFormatException ex) {
                
            }
        }
    }
    
    public int getArcadePlayers() {
        return this.arcadePlayers;
    }
    
    public int getArcadeSlots() {
        return this.arcadeSlots;
    }
    
    public String getMap() {
        return this.map;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    private void setData(int i, String value) throws NumberFormatException {
        switch (i) {
            case 0: this.map = value; break;
            case 1: this.status = Integer.parseInt(value); break;
            case 2: this.arcadePlayers = Integer.parseInt(value); break;
            case 3: this.arcadeSlots = Integer.parseInt(value); break;
        }
    }
}
