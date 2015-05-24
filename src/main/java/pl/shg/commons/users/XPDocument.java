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
import pl.shg.commons.database.Connection;
import pl.shg.commons.database.FutureTask;
import pl.shg.commons.documents.Document;
import pl.shg.commons.documents.DocumentArray;
import pl.shg.commons.documents.DocumentInfo;
import pl.shg.commons.documents.Documents;

/**
 * XP's management
 * @author Aleksander
 */
@DocumentInfo(
        name = "xp",
        strong = false,
        connection = Connection.USERS,
        documents = {NotificationDocument.class, UserDocument.class}
)
public class XPDocument extends Document {
    public XPDocument() {
        super();
    }
    
    // 0 - Integer - amount of XP
    // 1 - User - owner of this XP collection
    public void asyncCalculateXP(final DBUser user, final DocumentArray array) {
        this.query("SELECT xp FROM " + this.getName() + " WHERE user=?;", new Object[] { // TODO calculate
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
    
    public void asyncDropIfHas(DBUser user, int amount, String reason, FutureTask future) {
        this.runInsert(1, user, amount, reason, future);
    }
    
    public void asyncDropIfHasXP(final DBUser user, final int amount, final String reason) {
        this.asyncDropIfHas(user, amount, reason, new FutureTask() {
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
    
    public void asyncGive(DBUser user, int amount, String reason, FutureTask future) {
        this.runInsert(0, user, amount, reason, future);
    }
    
    public void asyncGiveXP(final DBUser user, final int amount, final String reason) {
        this.asyncGive(user, amount, reason, new FutureTask() {
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
        this.query("INSERT INTO " + this.getName() + " VALUES(NULL, " + type + ", ?, ?, ?);", new Object[] {
            amount,
            user.getUUID(),
            reason
        }, future);
    }
    
    public static XPDocument getDocument() {
        return (XPDocument) Documents.of(XPDocument.class);
    }
}
