package ba.unsa.etf.nsi.charlie.model.deserializer;

import ba.unsa.etf.nsi.charlie.model.*;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by koljenovic on 13/12/2016.
 */
public class ComponentDraftDeserializer implements JsonDeserializer<ComponentEntity> {
    @Override
    public ComponentEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return null;
    }
}
