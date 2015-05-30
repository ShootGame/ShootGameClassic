/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.documents;

import pl.shg.commons.database.Connection;
import pl.shg.commons.friends.FriendDocument;
import pl.shg.commons.helpers.IgnoreHelper;
import pl.shg.commons.users.NotificationDocument;
import pl.shg.commons.users.RankDocument;
import pl.shg.commons.users.UserDocument;

/**
 * Ignored players list management
 * @author Aleksander
 */
@DocumentInfo(
        name = "ignore",
        strong = false,
        connection = Connection.USERS,
        documents = {FriendDocument.class, NotificationDocument.class, RankDocument.class, UserDocument.class},
        helper = IgnoreHelper.class
)
public class IgnoreDocument extends Document {
    public IgnoreDocument() {
        super();
    }
}
