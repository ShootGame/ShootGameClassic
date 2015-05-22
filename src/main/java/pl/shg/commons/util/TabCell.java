/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.util;

import com.mojang.authlib.GameProfile;
import java.util.ArrayList;
import org.bukkit.entity.Player;

/**
 *
 * @author Aleksander
 */
public class TabCell extends Cell {
    private static final ArrayList<TabCell> list = new ArrayList<>();
    private String name; // nazwa komorki (niewidzialna, bo sa same kolory)
    private final GameProfile profile;
    private final int ping;
    private boolean update;
    private String displayName; // prefix (i suffix) czyli to co widac na tabie :>
    private final Player player;
    
    public TabCell(int index, String name, GameProfile profile, Player player) {
        super(index);
        this.name = name;
        this.profile = profile;
        this.ping = Tablists.PING;
        this.update = true;
        this.player = player;
    }
    
    public TabCell(int index, String name, GameProfile profile, int ping, Player player) {
        super(index);
        this.name = name;
        this.profile = profile;
        this.ping = ping;
        this.update = true;
        this.player = player;
    }
    
    public TabCell(int index, String name, GameProfile profile, int ping, String displayName, Player player) {
        super(index);
        this.name = name;
        this.profile = profile;
        this.ping = ping;
        this.update = true;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
    
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return this.displayName;
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
    
    public static ArrayList<TabCell> getCells() {
        return list;
    }
    
    public static TabCell get(String name) {
        for (TabCell cell : list) {
            if (cell.getName().equals(name)) {
                return cell;
            }
        }
        return null;
    }
    
    public static void add(TabCell cell) {
        list.add(cell);
    }
    
    public static void remove(TabCell cell) {
        cell.remove(cell);
    }
}
