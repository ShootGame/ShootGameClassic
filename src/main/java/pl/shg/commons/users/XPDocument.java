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
        documents = {NotificationDocument.class, UserDocument.class},
        helper = XPHelper.class
)
public class XPDocument extends Document {
    public XPDocument() {
        super();
    }
    
    public static XPDocument getDocument() {
        return (XPDocument) Documents.of(XPDocument.class);
    }
}
