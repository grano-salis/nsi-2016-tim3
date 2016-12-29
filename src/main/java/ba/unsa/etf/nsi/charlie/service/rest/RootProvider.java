package ba.unsa.etf.nsi.charlie.service.rest;

import ba.unsa.etf.nsi.charlie.service.rest.front.ModelFront;
import ba.unsa.etf.nsi.charlie.service.rest.front.UserFront;
import com.sun.net.httpserver.HttpServer;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

//Defines the base URI for all resource URIs.
@ApplicationPath("/")
public class RootProvider extends Application{
    //The method returns a non-empty collection with classes, that must be included in the published JAX-RS application
    @Override
    public Set<Class<?>> getClasses() {

        HashSet h = new HashSet<Class<?>>();
        h.add(ModelFront.class);
        h.add(UserFront.class);
        h.add(CORSFilter.class);
        return h;
    }
}