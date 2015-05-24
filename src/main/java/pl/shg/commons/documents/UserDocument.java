/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.documents;

import pl.shg.commons.database.Connection;

/**
 *
 * @author Aleksander
 */
@DocumentInfo(name = "user", strong = true, connection = Connection.USERS)
public class UserDocument extends Document {
    public UserDocument() {
        super();
    }
}
