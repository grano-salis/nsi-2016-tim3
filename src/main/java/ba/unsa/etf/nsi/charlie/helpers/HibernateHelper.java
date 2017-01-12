package ba.unsa.etf.nsi.charlie.helpers;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.TypeMismatchException;
import org.hibernate.cfg.Configuration;
import java.text.SimpleDateFormat;

/**
 * Created by koljenovic on 12/12/2016.
 */
public class HibernateHelper {

    public static SessionFactory factory = null;
    public static SessionFactory ssoFactory = null;

    private HibernateHelper() {
    }

    public static SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
    }

    private static synchronized SessionFactory initFactory() {
        try {
            if (factory == null) {
                Configuration configuration = new Configuration();
                configuration.configure();
                factory = configuration.buildSessionFactory();
            }
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
        return factory;
    }

    public static Session getSession() throws HibernateException {
        return initFactory().openSession();
    }

    private static synchronized SessionFactory initSSOFactory() {
        try {
            if (ssoFactory == null) {
                Configuration configuration = new Configuration();
                configuration.configure("sso.hib.cfg.xml");
                ssoFactory = configuration.buildSessionFactory();
            }
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
        return ssoFactory;
    }

    public static Session getSSOSession() throws HibernateException {
        return initFactory().openSession();
    }

}
