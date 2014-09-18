package ro.sync.tripexpenses.services;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.CookieParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.hibernate.Query;
import org.hibernate.Session;

import ro.sync.tripexpenses.singleton.SingletonSessionFactory;
import ro.sync.tripexpenses.tables.UserRegistered;

/**
 * A class generating the home page.
 * 
 * @author user
 */
@Path("/HomeServices")
public class HomeServices {
    @Context
    HttpServletRequest request;

    /**
     * Post method HomeServices Web Service.
     * 
     * @param sid
     *            The session cookie of the user.
     * @return the HTML content of the home page.
     */
    @POST
    @Produces("text/plain")
    public Response generateHome(@CookieParam(value = "sid") String sid) {
        String content;
        content = "<div role='main' class='ui-content' data-role='content'>\n";
        Session hiberSession = SingletonSessionFactory.getNewSession();
        try {
            hiberSession.beginTransaction();
            Query query = hiberSession
                    .createQuery("from UserRegistered where sessionID='" + sid
                            + "'");
            UserRegistered u = (UserRegistered) query.uniqueResult();
            content +=  "<p style='font-family:verdana;color:red;margin-left:100px;font-size:100px;'><b><u>Welcome " 
                    + u.getUserName() + "</u></b></p> "
                    + "<p style='color:blue;margin-left:50px;font-size:50px;'><b>you have " 
                    + u.getUserFriends().size() + " friends</b></p> "
                    + "<p style='font-family:verdana;color:blue;margin-left:40px;font-size:50px;'><b>you have " 
                    + u.getUserTrip().size()  + " active trips.</b></p> "
                    + "<p style='font-family:verdana;color:blue;margin-left:40px;font-size:50px;'>Email Adress : " 
                    + u.getEmailAdress() + " </p> " ;
        }catch (Exception ex) {
            System.out.println("home page exception");
        }
        hiberSession.getTransaction().commit();
        hiberSession.clear();
        hiberSession.close();
        content += "</div>";
        return Response.ok().entity(content).build();

    }
}
