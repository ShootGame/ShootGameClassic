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
@DocumentInfo(name = "xp", strong = false, connection = Connection.USERS)
public class XPDocument extends Document {
    public XPDocument() {
        super();
    }
}
