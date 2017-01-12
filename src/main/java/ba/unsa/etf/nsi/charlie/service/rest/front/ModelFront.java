package ba.unsa.etf.nsi.charlie.service.rest.front;

import ba.unsa.etf.nsi.charlie.helpers.HibernateHelper;
import ba.unsa.etf.nsi.charlie.helpers.RestHelper;
import ba.unsa.etf.nsi.charlie.model.ComponentDraftEntity;
import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TypeMismatchException;

import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ba.unsa.etf.nsi.charlie.helpers.GsonHelper;

import java.util.List;

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

        final Gson g = GsonHelper.getBuilder().create();
        Object entity = g.fromJson(jsonData, classType);
        s.saveOrUpdate(classType.cast(entity));
        try {
            t.commit();
        } catch (Exception e) {
            s.close();
            return Response.status(404).entity(r).build();
        }
        s.close();
        return Response.status(200)
                .entity(g.toJson(entity, classType))
                .build();
    }


    @PUT
    @Path("{entityType}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEntity(
            @PathParam("entityType") String entityType,
            String jsonData)
    {
        Session s = HibernateHelper.getSession();
        Transaction t = s.beginTransaction();
        String r = "{}";
        Class<?> classType = findClassType(entityType);

        final Gson g = GsonHelper.getBuilder().create();
        Object entity = g.fromJson(jsonData, classType);
        s.update(classType.cast(entity));
        try {
            t.commit();
        } catch (Exception e) {
            s.close();
            return Response.status(404).entity(r).build();
        }
        s.close();
        return Response.status(200)
                .entity(g.toJson(entity, classType))
                .build();
    }

    @GET
    @Path("{entityType}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readEntity(
            @PathParam("entityType") String entityType,
            @PathParam("id") Long id)
    {
        Class<?> classType = findClassType(entityType);
        return Response.status(200).entity(RestHelper.entityToJson(classType, id)).build();
    }

    @GET
    @Path("componentdrafts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchDrafts()
    {
        Session s = HibernateHelper.getSession();

        final Gson g = GsonHelper.getBuilder().create();
        Query q = s.createQuery("from ComponentDraftEntity");
        List<ComponentDraftEntity> c = q.getResultList();
        s.close();

        return Response.status(200).entity(g.toJson(c)).build();
    }

    @GET
    @Path("pendingdrafts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readCollection()
    {
        Session s = HibernateHelper.getSession();

        final Gson g = GsonHelper.getBuilder().create();
        Query q = s.createQuery("from ComponentDraftEntity where status = 'PENDING' order by id asc");
        List<ComponentDraftEntity> c = q.getResultList();
        s.close();

        return Response.status(200).entity(g.toJson(c)).build();
    }

    @DELETE
    @Path("{entityType}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEntity(
            @PathParam("entityType") String entityType,
            @PathParam("id") Long id)
    {
        Session s = HibernateHelper.getSession();
        String r = "{}";
        Class<?> classType = findClassType(entityType);

        try {
            Object entity = null;
            try {
                entity = s.get(classType, id);
            } catch (TypeMismatchException e) {
                entity = s.get(classType, id.intValue());
            } catch (Exception e) {
                s.close();
                e.printStackTrace();
            }
            if (entity != null) {
                final Gson g = GsonHelper.getBuilder().create();
                r = g.toJson(classType.cast(entity), classType);
                Transaction t = s.beginTransaction();
                s.delete(classType.cast(entity));
                t.commit();
            }
        } catch (Throwable e) {
            s.close();
            throw new ExceptionInInitializerError(e);
        } finally {
            s.close();
        }
        return Response.status(200).entity(r).build();
    }
}