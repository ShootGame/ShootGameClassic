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
public class MinecraftTarget extends TargetServer {
    public MinecraftTarget(String address, String id, String name, boolean publicy) throws NumberFormatException {
        super(address, id, name, publicy);
    }
    
    @Override
    public void read(String data) {
        PingResponse response = new Gson().fromJson(data, PingResponse.class);
        this.setPlayers(response.getPlayers().getOnline());
        this.setSlots(response.getPlayers().getMax());
    }
}
