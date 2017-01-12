package ba.unsa.etf.nsi.charlie.helpers;

import ba.unsa.etf.nsi.charlie.modelx.ComponentDraftEntity;
import ba.unsa.etf.nsi.charlie.modelx.ComponentEntity;
import ba.unsa.etf.nsi.charlie.modelx.ComponentTypeEntity;
import ba.unsa.etf.nsi.charlie.modelx.LogEntity;
import ba.unsa.etf.nsi.charlie.modelx.*;
import ba.unsa.etf.nsi.charlie.service.rest.deserializer.*;
import com.google.gson.GsonBuilder;

/**
 * Created by koljenovic on 14/12/2016.
 */
public class GsonHelper {

    private static GsonBuilder gsonBuilder = null;

    private GsonHelper() {
    }

    public static synchronized GsonBuilder getBuilder() {
        if (gsonBuilder == null) {
            gsonBuilder = new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            gsonBuilder.serializeNulls();
            gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
            gsonBuilder.registerTypeAdapter(ComponentEntity.class, new ComponentDeserializer());
            gsonBuilder.registerTypeAdapter(ComponentDraftEntity.class, new ComponentDraftDeserializer());
            gsonBuilder.registerTypeAdapter(ComponentTypeEntity.class, new ComponentTypeDeserializer());
            gsonBuilder.registerTypeAdapter(LogEntity.class, new LogDeserializer());
//            gsonBuilder.registerTypeAdapter(RoleEntity.class, new RoleDeserializer());
        }
        return gsonBuilder;
    }
}
