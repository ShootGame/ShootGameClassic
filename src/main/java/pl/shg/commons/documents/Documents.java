/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.documents;

import java.util.ArrayList;
import java.util.List;
import pl.shg.commons.friends.FriendDocument;
import pl.shg.commons.settings.SettingDocument;

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
    private static final List<Document> documents = new ArrayList<>();
    
    public static List<Document> getDocuments() {
        return documents;
    }
    
    public static Document of(Class<? extends Document> document) {
        for (Document doc : getDocuments()) {
            if (doc.getClass().equals(document)) {
                return doc;
            }
        }
        return null;
    }
    
    public static void register(Document document) {
        documents.add(document);
    }
    
    public static void registerDefault() {
        if (documents.isEmpty()) {
            for (Document document : defaults) {
                register(document);
            }
        }
    }
}
