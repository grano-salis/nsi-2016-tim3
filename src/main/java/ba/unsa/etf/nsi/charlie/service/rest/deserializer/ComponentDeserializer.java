package ba.unsa.etf.nsi.charlie.service.rest.deserializer;

import ba.unsa.etf.nsi.charlie.helpers.HibernateHelper;
import ba.unsa.etf.nsi.charlie.model.*;
import com.google.gson.*;
import org.hibernate.Session;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by koljenovic on 13/12/2016.
 */
public class ComponentDeserializer implements JsonDeserializer<ComponentEntity> {

    @Override
    public ComponentEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        final JsonObject jsonObject = jsonElement.getAsJsonObject();

        final long id;
        final long userid;
        final String title;
        final String updated;
        final String additionalinfo;
        final Long componenttype;
        final String data;

        try {
            id = jsonObject.get("id").getAsLong();
            userid = jsonObject.get("userid").getAsLong();
            title = jsonObject.get("title").getAsString();
//            updated = jsonObject.get("updated").getAsString();
            additionalinfo = jsonObject.get("additionalinfo").getAsString();
            componenttype = jsonObject.get("componenttype").getAsLong();
            data = jsonObject.get("data").getAsString();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(e);
        }

        ComponentEntity ce = new ComponentEntity();
        ce.setId(id);
        ce.setUserid(userid);
        ce.setTitle(title);
//        try {

//            ce.setUpdated(HibernateHelper.getDateFormat().parse(updated));
//        } catch (ParseException e) {
//            throw new IllegalArgumentException(e);
//        }
        ce.setUpdated(new Date());
        ce.setAdditionalinfo(additionalinfo);
        ce.setComponenttype(componenttype);
        ce.setData(data);
        Session s = HibernateHelper.getSession();

        ce.setComponentTypeByComponentType(s.get(ComponentTypeEntity.class, componenttype));

        s.close();
        return ce;
    }
}
