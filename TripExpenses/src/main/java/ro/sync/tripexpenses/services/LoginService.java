package ro.sync.tripexpenses.services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.hibernate.Query;
import org.hibernate.Session;

import ro.sync.tripexpenses.singleton.SIdTracker;
import ro.sync.tripexpenses.singleton.SingletonSessionFactory;
import ro.sync.tripexpenses.tables.UserRegistered;

/**
 * A class for handling requests.
 * 
 * @author Mihai
 */
@Path("/LoginService")
public class LoginService {

    /**
     * Post method Login Web Service.
     * 
     * @param username
     *            the user name of the user.
     * @param password
     *            the password the client provides.
     * @return the login response.
     */
    @POST
    @Produces("text/plain")
    public Response login(@FormParam("username") String username,
            @FormParam("password") String password) {
        UserRegistered user = null;
        Session loginSession = null;
        
        try {
            loginSession = SingletonSessionFactory.getNewSession();
            loginSession.beginTransaction();
            Query query = loginSession
                    .createQuery("from UserRegistered where userName='"
                            + username + "'");
            user = (UserRegistered) query.uniqueResult();
        }catch (Exception ex) {
            ex.printStackTrace();
        }

        if (user == null) {
            loginSession.clear();
            loginSession.close();
            return Response.ok().entity("NOT_REGISTERED").build();

        }else if (user.getUserPassword().equals(password)) {
            int cookieExp = 60 * 60;

            SIdTracker sIdTracker = SIdTracker.getInstance();
            String generatedSID = sIdTracker.generateSId();
            user.setSessionID(generatedSID);
            try {
                loginSession.update(user);
            }catch (Exception e) {
                e.printStackTrace();
            }
            loginSession.getTransaction().commit();
            loginSession.clear();
            loginSession.close();
            NewCookie cook = new NewCookie("sid", // name
                    generatedSID,// value
                    "/TripExpenses/", // path
                    null,// domain
                    "comment cookie sid",// comment
                    cookieExp,// seconds till expiration
                    false);// is the cookie sent only thru secured connection.
            return Response.ok().entity("USER_REGISTERED").cookie(cook).build();

        }else {
            loginSession.clear();
            loginSession.close();
            return Response.ok().entity("WRONG_PASSWORD").build();
        }
    }
}
