package ro.sync.tripexpenses.services.friends;

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
import ro.sync.tripexpenses.tables.UserRegistered;

/**
 * A service for creating a new trip..
 * 
 * @author Pomy.
 * 
 */
@Path("/AddFriendService")
public class AddFriendService {
    /**
     * @param sid the session cookie of the user.
     * @param friendName the name of the friend to be added.
     * @return a response.
     */
    @POST
    @Produces("text/plain")
    public Response addNewFriend(@CookieParam(value = "sid") String sid,
            @QueryParam("friendName") String friendName) {
    	//TODO: do serverside params check on all services
        
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
            	Query query2 = hiberSession
                        .createQuery("from UserRegistered where userName='" + friendName
                                + "'");
            	UserRegistered friend = (UserRegistered) query2.uniqueResult();
            	
            	if(friend != null && !friend.equals(user)) {
            		if(!user.getUserFriends().contains(friend)) {
						Collection<UserRegistered> userFriends = user.getUserFriends();
						userFriends.add(friend);
						user.setUserFriends(userFriends);

						Collection<UserRegistered> friendFriends = friend.getUserFriends();
						friendFriends.add(friend);
						friend.setUserFriends(friendFriends);

						hiberSession.update(friend);
						hiberSession.update(user);
						
						responseString = "SUCCESS";
            		} else {
                		responseString = "ALREADY_FRIENDS";
            		}
            	}
            }
            else {
                //TODO:
                //handle in javascript the messages.
                responseString = "NO_USER_FOUD";
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


