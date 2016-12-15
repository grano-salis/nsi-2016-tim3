package ba.unsa.etf.nsi.charlie.helpers;

import ba.unsa.etf.nsi.charlie.model.*;
import ba.unsa.etf.nsi.charlie.service.rest.deserializer.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.hibernate.Session;
import org.hibernate.TypeMismatchException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.concurrent.Callable;

/**
 * Created by koljenovic on 14/12/2016.
 */
public class RestHelper {

    static class CollectionWeird<X> implements ParameterizedType {

        private Class<?> wrapped;

        public CollectionWeird(Class<X> wrapped) {
            this.wrapped = wrapped;
        }

        public Type[] getActualTypeArguments() {
            return new Type[] {wrapped};
        }

        public Type getRawType() {
            return Collection.class;
        }

        public Type getOwnerType() {
            return null;
        }
    }

    public static String entityToJson(Class classType, Object id)
    {
        Session s = HibernateHelper.getSession();
        String r = "{}";

        try {
            Object entity = null;
            try {
                entity = s.get(classType, (Long) id);
            } catch (TypeMismatchException e) {
                entity = s.get(classType, ((Long) id).intValue());
            } catch (Exception e) {
                s.close();
                e.printStackTrace();
            }

            if (entity != null) {
                final Gson g = GsonHelper.getBuilder().create();
                r = g.toJson(classType.cast(entity), classType);
            }
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
        return r;
    }

    public static <entityType, collectionType> String collectionToJson(
            Class<entityType> entityClass,
            Class<collectionType> collectionClass,
            Long entityId,
            Method collectionGetter)
    {
        Session s = HibernateHelper.getSession();
        String r = "{}";

        try {
            entityType entity = null;
            try {
                entity = s.get(entityClass, (Long) entityId);
            } catch (TypeMismatchException e) {
                entity = s.get(entityClass, ((Long) entityId).intValue());
            } catch (Exception e) {
                s.close();
                e.printStackTrace();
            }

            if (entity != null) {
                Collection<collectionType> collection = (Collection<collectionType>) collectionGetter.invoke(entity);
                final Gson g = GsonHelper.getBuilder().create();
                r = g.toJson(collection, new CollectionWeird<collectionType>(collectionClass));
            }
        } catch (Throwable e) {
            s.close();
            throw new ExceptionInInitializerError(e);
        }
        s.close();
        return r;
    }
}
