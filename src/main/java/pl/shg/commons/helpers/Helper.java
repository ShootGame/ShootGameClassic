/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.helpers;

import pl.shg.commons.documents.Document;

/**
 *
 * @author Aleksander
 */
public class Helper {
    private final Document document;
    
    public Helper(Document document) {
        this.document = document;
    }
    
    public Document getDocument() {
        return this.document;
    }
}
