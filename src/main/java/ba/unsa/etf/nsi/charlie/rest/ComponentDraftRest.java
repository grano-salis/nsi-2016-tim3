package ba.unsa.etf.nsi.charlie.rest;

import ba.unsa.etf.nsi.charlie.HibernateHelper;
import ba.unsa.etf.nsi.charlie.model.ComponentDraftEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.Session;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/componentdraft")
public class ComponentDraftRest {
    @GET
    @Produces("application/json")
    public String getComponentDraft() {
        Session s = HibernateHelper.getSession();
        ComponentDraftEntity cde = s.get(ComponentDraftEntity.class, new Long(4));
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        final Gson g = gsonBuilder.create();
        return g.toJson(cde, ComponentDraftEntity.class);
    }
}