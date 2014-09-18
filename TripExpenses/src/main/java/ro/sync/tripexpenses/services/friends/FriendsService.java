package ro.sync.tripexpenses.services.friends;

import java.util.Collection;

import javax.ws.rs.CookieParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.hibernate.Query;
import org.hibernate.Session;

import ro.sync.tripexpenses.singleton.SingletonSessionFactory;
import ro.sync.tripexpenses.tables.UserRegistered;

/**
 * A class generating the friends list.
 * 
 * @author Mihai
 */
@Path("/FriendsService")
public class FriendsService {
    /**
     * Post method Friend Web Service.
     * 
     * @param sid
     *            The session cookie of the user.
     * @return the HTML content of the friends list.
     */
    @POST
    @Produces("text/plain")
    public Response generateFriendsList(@CookieParam(value = "sid") String sid) {
        String content;
        content = "<div role='main' class='ui-content' data-role='content'>\n"
                + "<div class='content-primary'>\n"
                + "<form>\n"
                + "<a href='javascript:addFriendForm();' class='ui-btn' data-theme='a'\n"
                + "data-icon='plus'>Add Friend</a>\n"
                + "<form class=\"ui-filterable\">"
                + "<input id=\"myFilter\" data-type=\"search\" placeholder=\"Friends\">"
                + "</form>"
                + "<ul id=\"friendsList\"data-role='listview' data-inset='true'\n"
                + "data-filter=\"true\" data-input=\"#myFilter\" data-inset=\"true\" data-theme='b'>\n";
                

        Collection<UserRegistered> friends = null;
        Session hiberSession = SingletonSessionFactory.getNewSession();
        try {
            hiberSession.beginTransaction();
            Query query = hiberSession
                    .createQuery("from UserRegistered where sessionID='" + sid
                            + "'");
            UserRegistered u = (UserRegistered) query.uniqueResult();
            friends = u.getUserFriends();
        }catch (Exception ex) {
            System.out.println("prieteni");
        }
        if (!friends.isEmpty()) {
            for (UserRegistered friend : friends) {
                String name = friend.getUserName();
                String email = friend.getEmailAdress();
                // adding the list elements
                // TODO: analyze the best way to get the email in javascript 
                //when friend is selected.
                // ...pass it as argument or detect by DOM
                String added = "<li data-name='" + email
                        + "'><a href='#'onclick=\"friendSelected(this); return false;\">" + name
                        + "</a></li>\n";
                content += added;
            }
        }else {
            content += "<li><a href='#'>"
                    +"Your Friend List is empty</a></li>\n";
        }
        hiberSession.getTransaction().commit();
        hiberSession.clear();
        hiberSession.close();
        content += "</ul></form></div></div>";
        return Response.ok().entity(content).build();

    }
}
