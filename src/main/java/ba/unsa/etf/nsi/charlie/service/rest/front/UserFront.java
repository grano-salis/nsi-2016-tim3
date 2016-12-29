package ba.unsa.etf.nsi.charlie.service.rest.front;

import ba.unsa.etf.nsi.charlie.helpers.*;
import ba.unsa.etf.nsi.charlie.model.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;

/**
 * Created by koljenovic on 14/12/2016.
 */
@Path("/rest/user/{id}/collection")
public class UserFront {

    @GET
    @Path("{collection}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComponents(
            @PathParam("id") Long id,
            @PathParam("collection") String collection)
    {
        String r = "{}";
        Method getter = null;
        try {
            switch (collection) {
                case "components":
                    getter = UserEntity.class.getDeclaredMethod("getComponentsById");
                    r = RestHelper.<UserEntity, ComponentEntity>collectionToJson(UserEntity.class, ComponentEntity.class, id, getter);
                    break;
                case "componentdrafts":
//                    getter = UserEntity.class.getDeclaredMethod("getComponentdraftsById");
//                    r = RestHelper.<UserEntity, ComponentDraftEntity>collectionToJson(UserEntity.class, ComponentDraftEntity.class, id, getter);
                    Session s = HibernateHelper.getSession();

                    final Gson g = GsonHelper.getBuilder().create();
                    Query q = s.createQuery("from ComponentDraftEntity where userid = :uid");
                    q.setParameter("uid", id);
                    List<ComponentDraftEntity> c = q.getResultList();

                    s.close();
                    r = g.toJson(c);
                    break;
                case "logs":
                    getter = UserEntity.class.getDeclaredMethod("getLogsById");
                    r = RestHelper.<UserEntity, LogEntity>collectionToJson(UserEntity.class, LogEntity.class, id, getter);
                    break;
                case "roles":
                    getter = UserEntity.class.getDeclaredMethod("getUserrolesById");
                    r = RestHelper.<UserEntity, UserRoleEntity>collectionToJson(UserEntity.class, UserRoleEntity.class, id, getter);
                    break;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return Response.status(200).entity(r).build();
    }
}
