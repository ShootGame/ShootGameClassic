/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.friends;

import pl.shg.commons.database.Connection;
import pl.shg.commons.documents.Document;
import pl.shg.commons.documents.DocumentInfo;
import pl.shg.commons.documents.IgnoreDocument;
import pl.shg.commons.users.NotificationDocument;
import pl.shg.commons.users.RankDocument;
import pl.shg.commons.users.UserDocument;

/**
 * Friendships management
 * @author Aleksander
 */
@DocumentInfo(
        name = "friend",
        strong = false,
        connection = Connection.USERS,
        documents = {IgnoreDocument.class, NotificationDocument.class, RankDocument.class, UserDocument.class},
        helper = FriendHelper.class
)
public class FriendDocument extends Document {
    public FriendDocument() {
        super();
    }
}
