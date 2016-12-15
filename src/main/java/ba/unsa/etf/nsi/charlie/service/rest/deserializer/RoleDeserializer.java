package ba.unsa.etf.nsi.charlie.service.rest.deserializer;

import ba.unsa.etf.nsi.charlie.helpers.HibernateHelper;
import ba.unsa.etf.nsi.charlie.model.RoleEntity;
import ba.unsa.etf.nsi.charlie.model.UserRoleEntity;
import com.google.gson.*;
import org.hibernate.Session;

import java.lang.reflect.Type;
import java.util.HashSet;

/**
 * Created by koljenovic on 15/12/2016.
 */
public class RoleDeserializer implements JsonDeserializer<RoleEntity> {


    @Override
    public RoleEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        final JsonObject jsonObject = jsonElement.getAsJsonObject();

        final Long id;
        final String name;

        try {
            id = jsonObject.get("id").getAsLong();
            name = jsonObject.get("name").getAsString();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(e);
        }

        RoleEntity entity = new RoleEntity();
        entity.setId(id);
        entity.setName(name);

        Session s = HibernateHelper.getSession();
        entity.setUserrolesById(new HashSet<UserRoleEntity>(s.createQuery("from UserRoleEntity t where t.roleid = :id").setParameter("id", id).list()));
//        entity.setUserByRoleId(new HashSet<UserRoleEntity>(s.createQuery("(from UserRoleEntity t where t.roleid = :id)").setParameter("id", id).list()));
        s.close();
        return entity;
    }
}
