package ro.sync.tripexpenses.services.trips;


import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


/**
 * A class generating the friends list.
 * 
 * @author user
 */
@Path("/TripCreateFormService")
public class TripCreateFormService {
    /**
     * Create the form for TripCreation.
     * 
     * @return the HTML content of the Create Trip Form.
     */
    @POST
    @Produces("text/plain")
    public Response generateTripCreateForm() {
        String content;
        content = "<div class=\"ui-field-contain\">\n" + 
        	    "            <input type=\"text\"\n" + 
        	    "                name=\"tripName\" id=\"tripN\" data-theme=\"b\" placeholder=\"Trip Name\"> " +
        	    "            <input type=\"text\" name=\"tripLocation\" id=\"tripL\" data-theme=\"b\" placeholder=\"Location\">\n" + 
        	    "            \n" + 
        	    "            <input type=\"text\" name=\"tripDescription\" id=\"tripD\" data-theme=\"b\" placeholder=\"Short Description\">\n" + 
        	    "            <label> </label>\n" + 
        	    "            <label>  </label>\n" + 
        	    "            <div class=\"ui-grid-a\">"
        	    + "<div class=\"ui-block-a\">"
				+ "<span> <a href=\"#\" data-role=\"button\" data-corners=\"true\""
				+ "	data-shadow=\"true\" data-iconshadow=\"true\" data-wrapperels=\"span\""
				+ "	data-theme=\"b\""
				+ "	class=\"ui-btn ui-shadow ui-btn-corner-all ui-btn-icon-left ui-btn-up-c ui-btn-b ui-icon-plus ui-nodisc-icon\" "
						+ "onclick=\"createTrip()\">"
						+ "<span class=\"ui-btn-inner ui-btn-corner-all\"> <span"
						+ "	class=\"ui-btn-text\"> Create </span> <span"
						+ "	class=\"ui-icon ui-icon-shadow\">&nbsp;</span></span>"
				+ "</a>"
				+ "</span>"
			+ "</div>"
			+ "<div class=\"ui-block-b\">"
			+ "	<span> <a href=\"#\" data-role=\"button\" data-corners=\"true\""
			+ "		data-shadow=\"true\" data-iconshadow=\"true\" data-wrapperels=\"span\""
			+ "		data-theme=\"b\""
			+ "		class=\"ui-btn ui-shadow ui-btn-corner-all ui-btn-icon-left ui-btn-up-c ui-btn-b ui-icon-delete ui-nodisc-icon\""
			+ "				 onclick=\"displayTrips()\">"
					+ "			<span class=\"ui-btn-inner ui-btn-corner-all\"> <span"
			+ "				class=\"ui-btn-text\">Cancel </span> <span"
			+ "				class=\"ui-icon ui-icon-delete ui-icon-shadow\">&nbsp;</span></span>"
			+ "	</a>"
			+ "	</span>"
			+ "</div>"
		+ "</div>"  
		+ "</div>";
        
//        content = "<h1> merge aici </h1";
        return Response.ok().entity(content).build();

    }
}
