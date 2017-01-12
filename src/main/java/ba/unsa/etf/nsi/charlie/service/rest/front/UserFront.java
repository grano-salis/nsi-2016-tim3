package ba.unsa.etf.nsi.charlie.service.rest.front;

import ba.unsa.etf.nsi.charlie.helpers.*;
import ba.unsa.etf.nsi.charlie.model.*;
import com.google.gson.Gson;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Method;
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
        Session s;
        final Gson g = GsonHelper.getBuilder().create();;
        Method getter = null;
        try {
            switch (collection) {
                case "components":
//                    getter = UserEntity.class.getDeclaredMethod("getComponentsById");
//                    r = RestHelper.<UserEntity, ComponentEntity>collectionToJson(UserEntity.class, ComponentEntity.class, id, getter);
                    s = HibernateHelper.getSession();

                    Query q = s.createQuery("from ComponentEntity where userid = :uid");
                    q.setParameter("uid", id);
                    List<ComponentEntity> c = q.getResultList();

                    s.close();
                    r = g.toJson(c);
                    break;
                case "componentdrafts":
//                    getter = UserEntity.class.getDeclaredMethod("getComponentdraftsById");
//                    r = RestHelper.<UserEntity, ComponentDraftEntity>collectionToJson(UserEntity.class, ComponentDraftEntity.class, id, getter);
                    s = HibernateHelper.getSession();

                    Query qc = s.createQuery("from ComponentDraftEntity where userid = :uid");
                    qc.setParameter("uid", id);
                    List<ComponentDraftEntity> cc = qc.getResultList();

                    s.close();
                    r = g.toJson(cc);
                    break;
                case "logs":
//                    getter = UserEntity.class.getDeclaredMethod("getLogsById");
//                    r = RestHelper.<UserEntity, LogEntity>collectionToJson(UserEntity.class, LogEntity.class, id, getter);
//                    break;
                case "roles":
//                    getter = UserEntity.class.getDeclaredMethod("getUserrolesById");
//                    r = RestHelper.<UserEntity, UserRoleEntity>collectionToJson(UserEntity.class, UserRoleEntity.class, id, getter);
//                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(200).entity(r).build();
    }
}
