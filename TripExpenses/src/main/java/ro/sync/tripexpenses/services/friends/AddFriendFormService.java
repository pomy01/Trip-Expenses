package ro.sync.tripexpenses.services.friends;


import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


/**
 * A class generating the friends list.
 * 
 * @author user
 */
@Path("/AddFriendFormService")
public class AddFriendFormService {
    /**
     * Create the form for TripCreation.
     * 
     * @return the HTML content of the Add Friend Form.
     */
    @POST
    @Produces("text/plain")
    public Response addFriendFormService() {
        String content;
        content = "<div class=\"ui-field-contain\">\n" + 
        	    "             <input type=\"text\"\n" + //<label for=\"friendName\"><b>Friend Name:</b></label>
        	    "                name=\"friendName\" id=\"friendN\" data-theme=\"b\" placeholder=\"Search for new friends\"> " +
        	    "            <label> </label>\n" + 
        	    "            <label>  </label>\n"
        		+ "	<div class=\"ui-grid-a\">"
        		+ "		<div class=\"ui-block-a\">"
        		+ "			<span> <a href=\"#\" data-role=\"button\" data-corners=\"true\""
        		+ "				data-shadow=\"true\" data-iconshadow=\"true\" data-wrapperels=\"span\""
        		+ "				data-theme=\"b\""
        		+ "				class=\"ui-btn ui-shadow ui-btn-corner-all ui-btn-icon-left ui-btn-up-c ui-btn-b ui-icon-plus" 
        		+ "ui-nodisc-icon\" onclick=\"addFriend()\">"
        		+ "					<span class=\"ui-btn-inner ui-btn-corner-all\"> <span"
        		+ "						class=\"ui-btn-text\"> Add Friend </span> <span"
        		+ "						class=\"ui-icon ui-icon-shadow\">&nbsp;</span></span>"
        		+ "			</a>"
        		+ "			</span>"
        		+ "		</div>"
        		+ "		<div class=\"ui-block-b\">"
        		+ "			<span> <a href=\"#\" data-role=\"button\" data-corners=\"true\""
        		+ "				data-shadow=\"true\" data-iconshadow=\"true\" data-wrapperels=\"span\""
        		+ "				data-theme=\"b\""
        		+ "				class=\"ui-btn ui-shadow ui-btn-corner-all ui-btn-icon-left ui-btn-up-c ui-btn-b ui-icon-delete"
        		+ "				ui-nodisc-icon\"  onclick=\"displayFriends()\">"
        		+ "					<span class=\"ui-btn-inner ui-btn-corner-all\"> <span"
        		+ "						class=\"ui-btn-text\">Cancel </span> <span"
        		+ "						class=\"ui-icon ui-icon-delete ui-icon-shadow\">&nbsp;</span></span>"
        		+ "			</a>"
        		+ "			</span>"
        		+ "		</div>"
        		+ "	</div>"
        	    + "        </div>";
        
        return Response.ok().entity(content).build();

    }
}
