/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.documents;

import pl.shg.commons.database.Connection;
import pl.shg.commons.helpers.MatchHelper;
import pl.shg.commons.users.NotificationDocument;

/**
 * Arcade's matches management
 * @author Aleksander
 */
@DocumentInfo(
        name = "match",
        strong = false,
        connection = Connection.USERS,
        documents = {KillDocument.class, NotificationDocument.class},
        helper = MatchHelper.class
)
public class MatchDocument extends Document {
    public MatchDocument() {
        super();
    }
}
