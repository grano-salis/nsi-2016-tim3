package ba.unsa.etf.nsi.charlie.model.deserializer;

import ba.unsa.etf.nsi.charlie.HibernateHelper;
import ba.unsa.etf.nsi.charlie.model.*;
import com.google.gson.*;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.hibernate.Session;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by koljenovic on 13/12/2016.
 */
public class ComponentDraftDeserializer implements JsonDeserializer<ComponentDraftEntity> {

    @Override
    public ComponentDraftEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        final JsonObject jsonObject = jsonElement.getAsJsonObject();

        final long id;
        final long componentid;
        final String status;
        final String additionalinfo;
        final long userid;
        final int componenttype;
        final String data;

        try {
            id = jsonObject.get("id").getAsLong();
            componentid = jsonObject.get("componentid").getAsLong();
            status = jsonObject.get("status").getAsString();
            additionalinfo = jsonObject.get("additionalinfo").getAsString();
            userid = jsonObject.get("userid").getAsLong();
            componenttype = jsonObject.get("componenttype").getAsInt();
            data = jsonObject.get("data").getAsString();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(e);
        }

        ComponentDraftEntity entity = new ComponentDraftEntity();
        entity.setId(id);
        entity.setComponentid(componentid);
        entity.setStatus(status);
        entity.setAdditionalinfo(additionalinfo);
        entity.setUserid(userid);
        entity.setComponenttype(componenttype);
        entity.setData(data);

        Session s = HibernateHelper.getSession();
        entity.setComponentByComponentid(s.get(ComponentEntity.class, componentid));
        entity.setUserByUserid(s.get(UserEntity.class, userid));
        entity.setComponenttypeByComponenttype(s.get(ComponentTypeEntity.class, componenttype));

        s.close();
        return entity;
    }
}
