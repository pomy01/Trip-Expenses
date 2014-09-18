/**
 * 
 */
package ro.sync.tripexpenses.services.facebook;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.restfb.DefaultFacebookClient;
import com.restfb.DefaultWebRequestor;
import com.restfb.Facebook;
import com.restfb.FacebookClient;
import com.restfb.WebRequestor;
import com.restfb.types.User;

/**
 * @author mihai
 */
@Path("/LoginFb")
public class LoginFb {
    
    /**
     * A token witch tells us if the user is valid or not!
     */
    @SuppressWarnings("unused")
    private static String clientToken;
    private static String appId = "450699658409076";
    private static String facebookAppUrl = "http://10.0.10.158:8080/TripExpenses/"; // It's
    @Facebook
    User me; // ..

    /**
     * Redirect to login page provides the code and then redirect to
     * facebookAppUrl
     * 
     * @return Returns a Redirect response to the facebookAppUrl that provides a
     *         code for the client.
     */
    @GET
    @Path("getInfo")
    @Produces("text/plain")
    public Response getInfo() {
//        System.out.println("facebook login works");
//        URI uir = UriBuilder.fromUri(
//                "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="
//                        + appId + "&redirect_uri=" + facebookAppUrl
//                        + "&scope=publish_stream,create_event").build();
//        return Response.temporaryRedirect(uir).build();
        return Response.status(200).entity("alabala").build();
    }

    /**
     * Generate the token based on the code , and redirects.
     * 
     * @param code
     *            The client code from facebook.
     * @return a request to facebook
     * @throws IOException
     */

    @GET
    @Path("verify")
    // Path must be the same as the facebookApiUrl
    @Produces("text/plain")
    public Response getCode(@QueryParam("code") String code) throws IOException {
        System.out.println("redirected by facebook");
        System.out.println(code);

        FacebookClient.AccessToken token = getFacebookUserToken(code, ""
                + facebookAppUrl);
        String accessToken = token.getAccessToken();
        //
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken);
        User user = facebookClient.fetchObject("me", User.class);

        System.out.println("User name: " + user.getName());

     //   Date expireDate = token.getExpires();
        System.out.println("Token:" + accessToken);
        clientToken = "" + accessToken;
        
        // after logging verification redirect to home page of the user.
        URI uir = UriBuilder.fromUri(
                "http://10.0.10.158:8080/TripExpenses/home.html").build();

        return Response.seeOther(uir).build();

    }

    /**
     * Generate a access token from facebook based on the parameters
     * <code>code<code>
     * and the facebook api url <code>redirectUrl<code>.
     * 
     * @param code
     *            The code of the user provided by a request to facebook.
     * @param redirectUrl
     *            Is the facebook api url.
     * @return The client token.
     * @throws IOException
     */
    private FacebookClient.AccessToken getFacebookUserToken(String code,
            String redirectUrl) throws IOException {

        String secretKey = "5834c942031a0dd3b72c0e6bbb08cfda";
        WebRequestor wr = new DefaultWebRequestor();
        WebRequestor.Response accessTokenResponse = wr
                .executeGet("https://graph.facebook.com/oauth/access_token?client_id="
                        + appId
                        + "&redirect_uri="
                        + redirectUrl
                        + "&client_secret="
                        + secretKey
                        + "&code="
                        + code
                        + "&response_type=token");

        return DefaultFacebookClient.AccessToken
                .fromQueryString(accessTokenResponse.getBody());
    }

}
