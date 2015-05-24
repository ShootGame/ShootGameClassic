/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.settings;

import java.util.UUID;
import pl.shg.commons.documents.DocumentArray;
import pl.shg.commons.documents.Documents;

/**
 *
 * @author Aleksander
 */
public class Setting {
    private final String name;
    private final PlayerSettingDefinition definition;
    
    public Setting(String name, PlayerSettingDefinition definition) {
        this.name = name.toLowerCase();
        this.definition = definition;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Setting) {
            return this.getName().equals(((Setting) obj).getName());
        }
        return false;
    }
    
    public void getAsyncDefinition(UUID uuid, DocumentArray array) {
        Documents.of(SettingDocument.class); // get player
    }
    
    public String getName() {
        return this.name;
    }
    
    public PlayerSettingDefinition getDefinition() {
        return this.definition;
    }
}
