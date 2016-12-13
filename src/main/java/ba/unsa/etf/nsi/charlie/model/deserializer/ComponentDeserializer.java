package ba.unsa.etf.nsi.charlie.model.deserializer;

import ba.unsa.etf.nsi.charlie.HibernateHelper;
import ba.unsa.etf.nsi.charlie.model.ComponentEntity;
import ba.unsa.etf.nsi.charlie.model.ComponentTypeEntity;
import ba.unsa.etf.nsi.charlie.model.UserEntity;
import com.google.gson.*;
import org.hibernate.Session;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by koljenovic on 13/12/2016.
 */
public class ComponentDeserializer implements JsonDeserializer<ComponentEntity> {

    @Override
    public ComponentEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        final JsonObject jsonObject = jsonElement.getAsJsonObject();

        final long id = jsonObject.get("id").getAsLong();
        final long userid = jsonObject.get("userid").getAsLong();
        final String title = jsonObject.get("title").getAsString();
        final String updated = jsonObject.get("updated").getAsString();
        final String additionalinfo = jsonObject.get("additionalinfo").getAsString();
        final Integer componenttype = jsonObject.get("componenttype").getAsInt();
        final String data = jsonObject.get("data").getAsString();

        ComponentEntity ce = new ComponentEntity();
        ce.setId(id);
        ce.setUserid(userid);
        ce.setTitle(title);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy h:m:s a"); // Dec 13, 2016 1:57:23 AM
        try {
            ce.setUpdated(dateFormat.parse(updated));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ce.setAdditionalinfo(additionalinfo);
        ce.setComponenttype(componenttype);
        ce.setData(data);

        Session s = HibernateHelper.getSession();
        ce.setUserByUserid(s.get(UserEntity.class, userid));
        ce.setComponenttypeByComponenttype(s.get(ComponentTypeEntity.class, componenttype));
        ce.setComponentdraftsById(s.createQuery("from ComponentDraftEntity t where t.componentid = :id").setParameter("id", id).list());

        s.close();
        return ce;
    }
}
