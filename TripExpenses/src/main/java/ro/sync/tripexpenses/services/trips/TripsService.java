package ro.sync.tripexpenses.services.trips;

import java.util.Collection;

import javax.ws.rs.CookieParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.hibernate.Query;
import org.hibernate.Session;

import ro.sync.tripexpenses.singleton.SingletonSessionFactory;
import ro.sync.tripexpenses.tables.*;

/**
 * A that generates the HTML content of the trip list.
 * 
 * @author Mihai.
 */
@Path("/TripsService")
public class TripsService {

    /**
     * Post method TripsService Web Service.
     * 
     * @param sid
     *            The sessionID cookie of the user making the request.
     * @return the trips html content.
     */
    @POST
    @Produces("text/plain")
    public Response generateTripsList(@CookieParam(value = "sid") String sid) {
        String content;
        
        content = "<div role='main' class='ui-content' data-role='content'>\n"
                + "<div class='content-primary'>\n"
                + "<form>\n"
                + "<a href='javascript:addTrip();' class='ui-btn' data-theme='a'\n"
                + "data-icon='plus'>Create new Trip</a>\n"
                + "<ul id=\"tripList\"data-role='listview' data-inset='true'\n"
                + "data-theme='b'>\n";
        // */

        Collection<UserTrip> trips = null;
        Session hiberSession = SingletonSessionFactory.getNewSession();
        try {
            hiberSession.beginTransaction();
            Query query = hiberSession
                    .createQuery("from UserRegistered where sessionID='" + sid
                            + "'");
            UserRegistered u = (UserRegistered) query.uniqueResult();
            trips = u.getUserTrip();

            if (!trips.isEmpty()) {
                for (UserTrip trip : trips) {
                    String id = trip.getTrip().getTripID();
                    String name = trip.getTrip().getName();
                    content += "<li data-name='"
                            + id
                            + "'><a href='#' onclick=\"tripSelected(this); return false;\">    "
                            + name + "</a></li>\n";
                }
            }else {
                content += "<li><a href='#'>Your Trip List is empty</a></li>\n";
            }
            hiberSession.getTransaction().commit();
            hiberSession.clear();
            hiberSession.close();
        }catch (Exception ex) {
            content.concat("<li><a href='javascript:errorAccesing();'>Error accesing your trips</a></li>\n");
            ex.printStackTrace();
        }
        content += "</ul></form></div></div>";
        return Response.ok().entity(content).build();
    }
}
