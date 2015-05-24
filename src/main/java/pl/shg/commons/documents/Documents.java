/*
 * Copyright (C) 2015 TheMolkaPL - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Aleksander Jagiełło <themolkapl@gmail.com>, 2014
 */
package pl.shg.commons.documents;

import java.util.ArrayList;
import java.util.List;

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
        new XPDocument(),
    };
    private static final List<Document> documents = new ArrayList<>();
    
    public static List<Document> getDocument() {
        return documents;
    }
    
    public static Document of(Class<? extends Document> document) {
        for (Document doc : getDocument()) {
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
