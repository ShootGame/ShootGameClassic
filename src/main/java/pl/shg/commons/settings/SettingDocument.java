/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.settings;

import pl.shg.commons.database.Connection;
import pl.shg.commons.documents.Document;
import pl.shg.commons.documents.DocumentInfo;
import pl.shg.commons.documents.UserDocument;

/**
 * Settings management
 * @author Aleksander
 */
@DocumentInfo(
        name = "setting",
        strong = false,
        connection = Connection.USERS,
        documents = {UserDocument.class}
)
public class SettingDocument extends Document {
    public SettingDocument() {
        super();
    }
}
