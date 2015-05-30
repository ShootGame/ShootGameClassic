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

/**
 * Server ranks management
 * @author Aleksander
 */
@DocumentInfo(
        name = "rank",
        strong = true,
        connection = Connection.USERS,
        documents = {NotificationDocument.class, UserDocument.class},
        helper = RankHelper.class
)
public class RankDocument extends Document {
    public RankDocument() {
        super();
    }
}
