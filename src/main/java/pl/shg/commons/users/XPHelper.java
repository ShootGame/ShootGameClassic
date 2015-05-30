/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import pl.shg.commons.database.FutureTask;
import pl.shg.commons.documents.DocumentArray;
import pl.shg.commons.helpers.Helper;

/**
 *
 * @author Aleksander
 */
public class XPHelper extends Helper {
    public XPHelper(XPDocument document) {
        super(document);
    }
    
    // 0 - Integer - amount of XP
    // 1 - User - owner of this XP collection
    public void calculateXP(final DBUser user, final DocumentArray array) {
        this.getDocument().query("SELECT xp FROM " + this.getDocument().getName() + " WHERE user=?;", new Object[] { // TODO calculate
            user.getUUID()
        }, new FutureTask() {
            @Override
            public void success(ResultSet result) {
                try {
                    while (result.next()) {
                        array.run(new Object[] {
                            result.getInt("xp"),
                            user
                        });
                        break;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(XPDocument.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        result.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(XPDocument.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
    
    public void dropIfHas(DBUser user, int amount, String reason, FutureTask future) {
        this.runInsert(1, user, amount, reason, future);
    }
    
    public void dropIfHasXP(final DBUser user, final int amount, final String reason) {
        this.dropIfHas(user, amount, reason, new FutureTask() {
            @Override
            public void success(ResultSet result) {
                Player player = Bukkit.getPlayer(user.getUUID());
                if (player != null) {
                    String message = ChatColor.AQUA + "Straciles/as " + ChatColor.DARK_RED + ChatColor.BOLD + amount + " XP";
                    if (reason != null) {
                        message += ChatColor.GRAY + " - " + reason;
                    }
                    player.sendMessage(message + ChatColor.RESET + ChatColor.AQUA + ".");
                }
            }
        });
    }
    
    public void give(DBUser user, int amount, String reason, FutureTask future) {
        this.runInsert(0, user, amount, reason, future);
    }
    
    public void giveXP(final DBUser user, final int amount, final String reason) {
        this.give(user, amount, reason, new FutureTask() {
            @Override
            public void success(ResultSet result) {
                Player player = Bukkit.getPlayer(user.getUUID());
                if (player != null) {
                    String message = ChatColor.AQUA + "Otrzymales/as " + ChatColor.GREEN + ChatColor.BOLD + amount + " XP";
                    if (reason != null) {
                        message += ChatColor.GRAY + " - " + reason;
                    }
                    player.sendMessage(message + ChatColor.RESET + ChatColor.AQUA + ".");
                }
            }
        });
    }
    
    protected void runInsert(int type, DBUser user, int amount, String reason, FutureTask future) {
        this.getDocument().query("INSERT INTO " + this.getDocument().getName() + " VALUES(NULL, " + type + ", ?, ?, ?);", new Object[] {
            amount,
            user.getUUID(),
            reason
        }, future);
    }
}
