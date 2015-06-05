/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.server;

import org.bukkit.Material;
import com.google.gson.Gson;

/**
 *
 * @author Aleksander
 */
public class MinecraftTarget extends TargetServer {
    private final Material icon;
    
    public MinecraftTarget(String address, Material icon, String id, String name, boolean publicy) throws NumberFormatException {
        super(address, id, name, publicy);
        this.icon = icon;
    }
    
    @Override
    public void read(String data) {
        PingResponse response = new Gson().fromJson(data, PingResponse.class);
        this.setPlayers(response.getPlayers().getOnline());
        this.setSlots(response.getPlayers().getMax());
    }
    
    public Material getIcon() {
        return this.icon;
    }
}
