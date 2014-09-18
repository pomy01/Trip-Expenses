/**
 * A class that provides singleton instances.
 */
package ro.sync.tripexpenses.singleton;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;

/**
 * @author user
 *
 */
public class SingletonSessionFactory {
    
    private static SessionFactory sessionFactory = null;
    
    /**
     * Constructor private to prevent construction of other instances.
     */
    private SingletonSessionFactory() { }
    /**
     * Creates a unique instance of a session factory.
     * @return a singleton for SessionFactory.
     */
    @SuppressWarnings("deprecation")
    public static SessionFactory getSessionFactoryInstance() {
        if(sessionFactory == null) {
            sessionFactory = new Configuration().configure()
                    .buildSessionFactory();
            }
        return sessionFactory;
     }
    /**
     * Creates a new session from the singleton Session Factory.
     * @return a new session.
     */
    @SuppressWarnings("deprecation")
    public static Session getNewSession(){
        if(sessionFactory == null) {
            sessionFactory = new Configuration().configure()
                    .buildSessionFactory();
            }
        return sessionFactory.openSession();
    }
    
    /**
     * Shuts down the session factory.
     */
    public static void shutDownFactory() {
        sessionFactory.close();
    }
}
