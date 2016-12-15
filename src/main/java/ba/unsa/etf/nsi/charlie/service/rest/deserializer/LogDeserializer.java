package ba.unsa.etf.nsi.charlie.service.rest.deserializer;

import ba.unsa.etf.nsi.charlie.helpers.HibernateHelper;
import ba.unsa.etf.nsi.charlie.model.*;
import com.google.gson.*;
import org.hibernate.Session;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by koljenovic on 13/12/2016.
 */
public class LogDeserializer implements JsonDeserializer<LogEntity> {
    @Override
    public LogEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        final JsonObject jsonObject = jsonElement.getAsJsonObject();

        final Integer id;
        final Long userId;
        final String logText;
        final String created;

        try {
            id = jsonObject.get("id").getAsInt();
            userId = jsonObject.get("userId").getAsLong();
            logText = jsonObject.get("logText").getAsString();
            created = jsonObject.get("created").getAsString();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(e);
        }

        LogEntity entity = new LogEntity();
        entity.setId(id);
        entity.setUserId(userId);
        entity.setLogText(logText);
        entity.setCreated(new Date());

        Session s = HibernateHelper.getSession();
        entity.setUserByUserId(s.get(UserEntity.class, userId));

        s.close();
        return entity;

    }
}
