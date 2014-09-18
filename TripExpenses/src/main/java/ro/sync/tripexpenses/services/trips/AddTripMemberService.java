package ro.sync.tripexpenses.services.trips;

import java.util.Collection;

import javax.ws.rs.CookieParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ro.sync.tripexpenses.singleton.SingletonSessionFactory;
import ro.sync.tripexpenses.tables.Trip;
import ro.sync.tripexpenses.tables.UserRegistered;
import ro.sync.tripexpenses.tables.UserTrip;
import ro.sync.tripexpenses.tables.UserTripId;

/**
 * A service for creating a new trip..
 * 
 * @author Pomy.
 */
@Path("/AddTripMemberService")
public class AddTripMemberService {
    /**
     * @param sid
     *            the session cookie of the user.
     * @param tripCredential
     *            the trip unique credential.
     * @param newMemberName
     *            the name of the member to be invited.
     * @return a response.
     */
    @POST
    @Produces("text/plain")
    public Response addMember(@CookieParam(value = "sid") String sid,
            @QueryParam("tripCredential") String tripCredential,
            @QueryParam("newMemberName") String newMemberName) {

        Session hiberSession = SingletonSessionFactory.getNewSession();
        Transaction tx = null;
        UserRegistered user = null;
        UserRegistered newMember = null;
        UserTrip userTrip = null;

        Trip trip = null;
        String responseString = "";
        try {
            tx = hiberSession.beginTransaction();
            Query query = hiberSession
                    .createQuery("from UserRegistered where sessionID='" + sid
                            + "'");
            user = (UserRegistered) query.uniqueResult();
            Query userTripQuery = hiberSession
                    .createQuery("from UserTrip where user_id='"
                            + user.getUserID() + "' and trip_id='"
                            + tripCredential + "'");
            userTrip = (UserTrip) userTripQuery.uniqueResult();
            Query query2 = hiberSession
                    .createQuery("from UserRegistered where userName='"
                            + newMemberName + "'");
            newMember = (UserRegistered) query2.uniqueResult();
            if (newMember != null) {
                UserTrip newUserTrip = new UserTrip();
                newUserTrip.setJoinedTrip(false);

                UserTripId userTripId = new UserTripId();
                userTripId.setTrip(userTrip.getTrip());
                userTripId.setUser(newMember);
                newUserTrip.setUserTripId(userTripId);
//                hiberSession.save(newUserTrip);

                trip = userTrip.getTrip();
                Collection<UserTrip> userTripList = trip.getUserTrip();
                userTripList.add(newUserTrip);
                trip.setUserTrip(userTripList);
                userTrip.setTrip(trip);
                hiberSession.update(userTrip);
                
//                Collection<UserTrip> newMemberUserTrip = newMember.getUserTrip();
//                newMemberUserTrip.add(newUserTrip);
//                newMember.setUserTrip(newMemberUserTrip);
                hiberSession.update(newMember);

                tx.commit();
            }
        }catch (Exception ex) {
            if (tx!=null) tx.rollback();
            ex.printStackTrace();
        }
        hiberSession.flush();
        hiberSession.clear();
        hiberSession.close();
        return Response.ok().entity(responseString).build();
    }
}
