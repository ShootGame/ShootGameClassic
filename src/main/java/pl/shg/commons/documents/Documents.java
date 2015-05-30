/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.documents;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pl.shg.commons.friends.FriendDocument;
import pl.shg.commons.settings.SettingDocument;
import pl.shg.commons.users.NotificationDocument;
import pl.shg.commons.users.RankDocument;
import pl.shg.commons.users.UserDocument;
import pl.shg.commons.users.UsernameDocument;
import pl.shg.commons.users.XPDocument;

/**
 *
 * @author Aleksander
 */
public class Documents {
    private static final Document[] defaults = new Document[] {
        new FriendDocument(),
        new IgnoreDocument(),
        new KillDocument(),
        new MatchDocument(),
        new NotificationDocument(),
        new PunishmentDocument(),
        new RankDocument(),
        new RateDocument(),
        new ReportDocument(),
        new SettingDocument(),
        new UserDocument(),
        new UsernameDocument(),
        new XPDocument(),
    };
    private static final Map<Class<? extends Document>, Document> documents = new HashMap<>();
    
    public static Map<Class<? extends Document>, Document> getDocuments() {
        return documents;
    }
    
    public static Document of(Class<? extends Document> document) {
        return documents.getOrDefault(document, null);
    }
    
    public static void register(Document document) {
        documents.put(document.getClass(), document);
    }
    
    public static void registerDefault() {
        if (documents.isEmpty()) {
            for (Document document : defaults) {
                register(document);
            }
        }
    }
}
