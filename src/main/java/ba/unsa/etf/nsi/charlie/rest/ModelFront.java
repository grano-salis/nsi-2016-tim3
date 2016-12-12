package ba.unsa.etf.nsi.charlie.rest;

import ba.unsa.etf.nsi.charlie.HibernateHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.Session;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/rest")
public class ModelFront {

    @GET
    @Path("{entityType}/{id}")
    @Produces("application/json")
    public String getEntity(
            @PathParam("entityType") String entityType,
            @PathParam("id") Long id)
    {
        Session s = HibernateHelper.getSession();
        String r = "{}";
        Class<?> classType = null;

        try {
            switch (entityType) {
                case "component":
                    classType = Class.forName("ba.unsa.etf.nsi.charlie.model.ComponentEntity");
                    break;
                case "componentdraft":
                    classType = Class.forName("ba.unsa.etf.nsi.charlie.model.ComponentDraftEntity");
                    break;
                case "componenttype":
                    classType = Class.forName("ba.unsa.etf.nsi.charlie.model.ComponentTypeEntity");
                    break;
                case "log":
                    classType = Class.forName("ba.unsa.etf.nsi.charlie.model.LogEntity");
                    break;
                case "role":
                    classType = Class.forName("ba.unsa.etf.nsi.charlie.model.RoleEntity");
                    break;
                case "sso_user":
                    classType = Class.forName("ba.unsa.etf.nsi.charlie.model.UserEntity");
                    break;
                case "userinfo":
                    classType = Class.forName("ba.unsa.etf.nsi.charlie.model.UserInfoEntity");
                    break;
                case "userrole":
                    classType = Class.forName("ba.unsa.etf.nsi.charlie.model.UserRoleEntity");
                    break;
                default:
                    throw new IllegalArgumentException("Invalid API endpoint: " + entityType);
            }

            Object cde = s.get(classType, id);
            if (cde != null) {
                final GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.setPrettyPrinting();
                final Gson g = gsonBuilder.create();
                r = g.toJson(classType.cast(cde), classType);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        } finally {
            s.close();
        }
        return r;
    }
}