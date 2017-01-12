package ba.unsa.etf.nsi.charlie.service.rest.deserializer;

import ba.unsa.etf.nsi.charlie.helpers.HibernateHelper;
import ba.unsa.etf.nsi.charlie.model.*;
import com.google.gson.*;
import org.hibernate.Session;

import java.lang.reflect.Type;
import java.util.HashSet;

/**
 * Created by koljenovic on 13/12/2016.
 */
public class ComponentTypeDeserializer implements JsonDeserializer<ComponentTypeEntity> {
    @Override
    public ComponentTypeEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        final JsonObject jsonObject = jsonElement.getAsJsonObject();

        final Integer id;
        final String componenttype;

        try {
            id = jsonObject.get("id").getAsInt();
            componenttype = jsonObject.get("componenttype").getAsString();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(e);
        }

        ComponentTypeEntity entity = new ComponentTypeEntity();
        entity.setId(id);
        entity.setComponenttype(componenttype);

        Session s = HibernateHelper.getSession();
        entity.setComponentsById(new HashSet<ComponentEntity>(s.createQuery("from ComponentEntity t where t.componenttype = :id").setParameter("id", id).list()));

        s.close();
        return entity;
    }
}
