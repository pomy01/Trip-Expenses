package ro.sync.tripexpenses.services;

import javax.ws.rs.CookieParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.hibernate.Query;
import org.hibernate.Session;

import ro.sync.tripexpenses.singleton.SIdTracker;
import ro.sync.tripexpenses.singleton.SingletonSessionFactory;
import ro.sync.tripexpenses.tables.UserRegistered;

/**
 * Handle the user logging out.
 * 
 * @author Mihai.
 */
@Path("/LogoutService")
public class LogoutService {
    /**
     * Method for logging out the user.
     * 
     * @param sid
     *            The session cookie of the user.
     * @return a response to delete the session cookie.
     */
    @POST
    public Response logoutUser(@CookieParam(value = "sid") String sid) {
        System.out.println("logout initialized");
        Session hiberSession = SingletonSessionFactory.getNewSession();
        try {
            hiberSession.beginTransaction();
            Query query = hiberSession
                    .createQuery("from UserRegistered where sessionID='" + sid
                            + "'");
            UserRegistered user = (UserRegistered) query.uniqueResult();
            if(user != null) {
            user.setSessionID(null);
            }
            SIdTracker sIdTracker = SIdTracker.getInstance();
            sIdTracker.removeSId(sid);
            hiberSession.update(user);
        }catch (Exception ex) {
            System.out.println("logout failed");
        }
        hiberSession.getTransaction().commit();
        hiberSession.clear();
        hiberSession.close();
        
        NewCookie cook = new NewCookie("sid", // name
                null,// value
                "/TripExpenses/", // path
                null,// domain
                "comment cookie sid",// comment
                1,// seconds till expiration
                false);// is the cookie sent only thru secured connection.
        return Response.ok().cookie(cook).build();
    }
}
