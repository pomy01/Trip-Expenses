package ro.sync.tripexpenses.services.trips;

import javax.ws.rs.CookieParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.hibernate.Query;
import org.hibernate.Session;

import ro.sync.tripexpenses.singleton.SingletonSessionFactory;
import ro.sync.tripexpenses.tables.Trip;
import ro.sync.tripexpenses.tables.UserRegistered;
import ro.sync.tripexpenses.tables.UserTrip;

/**
 * A service for creating a new payment.
 * 
 * @author Mihai.
 * 
 */
@Path("/ReopenTripService")
public class ReopenTripService {
    /**
     * @param sid 
     *             The session cookie of the user.
     * @param tripCredential the credential to identify the trip.
     * @return a response.
     */
    @POST
    @Produces("text/plain")
    public Response reopen(@CookieParam(value = "sid") String sid,
            @QueryParam("tripCredential") String tripCredential) {
        Trip trip = null;
        UserTrip userTrip = null;
        UserRegistered user = null;
        String responseString = "";
        Session hiberSession = SingletonSessionFactory.getNewSession();
        try {
            hiberSession.beginTransaction();
            Query userQuery = hiberSession
                    .createQuery("from UserRegistered where sessionID='" + sid + "'");
            user = (UserRegistered)userQuery.uniqueResult();
            
            Query userTripQuery = hiberSession
                    .createQuery("from UserTrip where user_id='" + user.getUserID()
                            + "' and trip_id='" + tripCredential + "'");
            userTrip = (UserTrip)userTripQuery.uniqueResult();
            trip = userTrip.getTrip();
            //change the isClosed
            //TODO: create a new service to reopen and a new javascript function
            trip.setClosed(false);
            userTrip.setTrip(trip);
            responseString = "SUCCESS";
            hiberSession.update(userTrip);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        hiberSession.getTransaction().commit();
        hiberSession.clear();
        hiberSession.close();
        return Response.ok().entity(responseString).build();
    }
}


