package ba.unsa.etf.nsi.charlie.rest;

import ba.unsa.etf.nsi.charlie.HibernateHelper;
import ba.unsa.etf.nsi.charlie.model.ComponentDraftEntity;
import com.google.gson.Gson;
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
        Gson g = new Gson();
        return g.toJson(cde, ComponentDraftEntity.class);
    }
}