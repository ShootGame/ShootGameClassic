/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.documents;

import pl.shg.commons.database.Connection;

/**
 * Username's history management
 * @author Aleksander
 */
@DocumentInfo(
        name = "username",
        strong = true,
        connection = Connection.USERS,
        documents = {UserDocument.class}
)
public class UsernameDocument extends Document {
    public UsernameDocument() {
        super();
    }
}
