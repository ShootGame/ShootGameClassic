/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.shootgame.api.server;

import org.bukkit.craftbukkit.libs.com.google.gson.Gson;
import pl.shg.shootgame.api.util.ArcadeData;

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
        
        Object[] result = ArcadeData.fromData(response.getDescription());
        try {
            this.map = (String) result[0];
            this.status = (Integer) result[1];
            this.arcadePlayers = (Integer) result[2];
            this.arcadeSlots = (Integer) result[3];
        } catch (NullPointerException ex) {}
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
}
