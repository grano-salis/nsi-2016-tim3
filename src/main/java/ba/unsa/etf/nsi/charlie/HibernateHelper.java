package ba.unsa.etf.nsi.charlie;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * Created by koljenovic on 12/12/2016.
 */
public class HibernateHelper {

    private static SessionFactory initFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return initFactory().openSession();
    }
}
