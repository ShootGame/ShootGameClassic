/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.users;

import pl.shg.commons.database.Connection;
import pl.shg.commons.documents.Document;
import pl.shg.commons.documents.DocumentInfo;
import pl.shg.commons.settings.SettingDocument;

/**
 * Notifications on the website or game's side management
 * @author Aleksander
 */
@DocumentInfo(
        name = "notification",
        strong = false,
        connection = Connection.USERS,
        documents = {SettingDocument.class, UserDocument.class},
        helper = NotificationHelper.class
)
public class NotificationDocument extends Document {
    public NotificationDocument() {
        super();
    }
}
