/**
 * 
 */
package ro.sync.tripexpenses.models;

import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Session;

import ro.sync.tripexpenses.singleton.SingletonSessionFactory;
import ro.sync.tripexpenses.tables.UserRegistered;

/**
 * Business Service for Hibernate.
 * @author user
 *
 */
public class HibernateUtil {
    /**
     * REturns the friend list of a user.
     * @param sid the sessionID of the user.
     * @return the friend list.
     */
    public static Collection<UserRegistered> FriendList(String sid) {
      //get the list of friends
        try {
        Session session = SingletonSessionFactory.getNewSession();
        session.beginTransaction();
        Collection<UserRegistered> list = null;
            Query query = session
                    .createQuery("from UserRegistered where sessionID='"
                            + sid + "'");
            UserRegistered u = (UserRegistered) query.uniqueResult();
            list = u.getUserFriends();
            session.getTransaction().commit();
            session.close();
            return list;
        }catch (Exception ex) {
           return null;
        }
    }
    
    
}
