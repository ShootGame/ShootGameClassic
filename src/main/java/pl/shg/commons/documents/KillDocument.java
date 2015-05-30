/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.documents;

import pl.shg.commons.database.Connection;
import pl.shg.commons.helpers.KillHelper;
import pl.shg.commons.users.UserDocument;

/**
 * Kills with deaths managements
 * @author Aleksander
 */
@DocumentInfo(
        name = "kill",
        strong = false,
        connection = Connection.USERS,
        documents = {UserDocument.class},
        helper = KillHelper.class
)
public class KillDocument extends Document {
    public KillDocument() {
        super();
    }
}
