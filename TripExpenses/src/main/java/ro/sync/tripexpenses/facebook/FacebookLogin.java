/**
 * 
 */
package ro.sync.tripexpenses.facebook;


import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * @author user
 *
 */
@Path("/FacebookLogin")
public class FacebookLogin {
    /**
     * Facebook login service.
     * @param message have no idea.
     * @return null.
     */
    @POST
    public Response Login(@QueryParam("message")  String message) {

        System.out.println("the request has been received");
        return null;

    }

}
