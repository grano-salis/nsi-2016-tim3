package ba.unsa.etf.nsi.charlie.rest;

import ba.unsa.etf.nsi.charlie.HibernateHelper;
import ba.unsa.etf.nsi.charlie.model.*;
import ba.unsa.etf.nsi.charlie.model.deserializer.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rest")
public class ModelFront {

    protected Class<?> findClassType(final String entityType) {
        try {
            switch (entityType) {
                case "component":
                    return Class.forName("ba.unsa.etf.nsi.charlie.model.ComponentEntity");
                case "componentdraft":
                    return Class.forName("ba.unsa.etf.nsi.charlie.model.ComponentDraftEntity");
                case "componenttype":
                    return Class.forName("ba.unsa.etf.nsi.charlie.model.ComponentTypeEntity");
                case "log":
                    return Class.forName("ba.unsa.etf.nsi.charlie.model.LogEntity");
                case "role":
                    return Class.forName("ba.unsa.etf.nsi.charlie.model.RoleEntity");
                case "user":
                    return Class.forName("ba.unsa.etf.nsi.charlie.model.UserEntity");
                case "userinfo":
                    return Class.forName("ba.unsa.etf.nsi.charlie.model.UserInfoEntity");
                case "userrole":
                    return Class.forName("ba.unsa.etf.nsi.charlie.model.UserRoleEntity");
                default:
                    throw new IllegalArgumentException("Invalid API endpoint: " + entityType);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @POST
    @Path("{entityType}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveEntity(
            @PathParam("entityType") String entityType,
            String jsonData)
    {
        Session s = HibernateHelper.getSession();
        Transaction t = s.beginTransaction();
        String r = "{}";
        Class<?> classType = findClassType(entityType);

        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.serializeNulls();
        gsonBuilder.registerTypeAdapter(ComponentEntity.class, new ComponentDeserializer());
        final Gson g = gsonBuilder.create();
        ComponentEntity ce = g.fromJson(jsonData, ComponentEntity.class);
        s.flush();
        s.saveOrUpdate(ce);
        t.commit();
        s.close();
        return Response.status(200).entity(g.toJson(ce, ComponentEntity.class)).build();
    }

    @GET
    @Path("{entityType}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEntity(
            @PathParam("entityType") String entityType,
            @PathParam("id") Long id)
    {
        Session s = HibernateHelper.getSession();
        String r = "{}";
        Class<?> classType = findClassType(entityType);

        try {
            Object cde = s.get(classType, id);
            if (cde != null) {
                final GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.setPrettyPrinting();
                final Gson g = gsonBuilder.create();
                r = g.toJson(classType.cast(cde), classType);
            }
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        } finally {
            s.close();
        }
        return Response.status(200).entity(r).build();
    }
}