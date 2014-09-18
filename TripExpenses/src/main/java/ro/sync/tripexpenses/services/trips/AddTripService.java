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

import ro.sync.tripexpenses.singleton.SingletonSessionFactory;
import ro.sync.tripexpenses.tables.Trip;
import ro.sync.tripexpenses.tables.UserRegistered;
import ro.sync.tripexpenses.tables.UserTrip;

/**
 * A service for creating a new trip..
 * 
 * @author Mihai.
 * 
 */
@Path("/AddTripService")
public class AddTripService {
    /**
     * @param sid the session cookie of the user.
     * @param tripName The name of the trip to be created.
     * @param tripLocation the location of the trip.
     * @param tripDescription the trip description.
     * @return a response.
     */
    @POST
    @Produces("text/plain")
    public Response addNewTrip(@CookieParam(value = "sid") String sid,
            @QueryParam("tripName") String tripName,
            @QueryParam("tripLocation") String tripLocation,
            @QueryParam("tripDescription") String tripDescription) {
        
        Session hiberSession = SingletonSessionFactory.getNewSession();
        UserRegistered user = null;
        String responseString = "";
        try {
            hiberSession.beginTransaction();
            Query query = hiberSession
                    .createQuery("from UserRegistered where sessionID='" + sid
                            + "'");
            user = (UserRegistered) query.uniqueResult();
            if (user != null) {
                Trip trip = new Trip();
                trip.setCreator(user);
                trip.setName(tripName);
                trip.setTripDescription(tripDescription);
                trip.setTripLocation(tripLocation);
                
                UserTrip userTrip = new UserTrip();
                userTrip.setTrip(trip);
                userTrip.getUserTripId().setTrip(trip);
                userTrip.getUserTripId().setUser(user);
                userTrip.setJoinedTrip(true);
                
                Collection <UserTrip> trips =user.getUserTrip();
                trips.add(userTrip);
                 user.setUserTrip(trips);
                 Collection<Trip> createdTrips = user.getCreatedTrips();
                 createdTrips.add(trip);
                 user.setCreatedTrips(createdTrips);
                
                hiberSession.update(user);
                hiberSession.save(trip);
                hiberSession.save(userTrip);
                
                //TODO: get the get id of the trip right.
                
//                responseString = "<li data-name='" + trip.getTripID()
//                        + "'><a href='javascript:tripSelected();'>" + trip.getName()
//                        + "</a></li>\n";
                responseString = "ADDED_SUCCESFULLY";
            }
            else {
                //TODO:
                //add it to the list after creation.
                responseString = "CREATION_FAILED";
            }
        } catch (Exception ex) {
            System.out.println("AddTripService database access failed");
        }
        hiberSession.flush();
        hiberSession.getTransaction().commit();
        hiberSession.clear();
        hiberSession.close();
        return Response.ok().entity(responseString).build();
    }
}


