package ro.sync.tripexpenses.services.payments;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * A class generating the friends list.
 * 
 * @author user
 */
@Path("/PaymentCreateFormService")
public class PaymentCreateFormService {
    /**
     * Create the form for TripCreation.
     * 
     * @return the HTML content of the Create Trip Form.
     */
    @POST
    @Produces("text/plain")
    public Response generatePaymentCreateForm() {
        String content;
        content = "<div class=\"ui-field-contain\">\n"
                + "            <input type=\"text\"\n"
                + "                name=\"paymentDescription\" id=\"paymentD\" data-theme=\"b\" placeholder=\"Short Payment Description\"> "
                + "            <input type=\"number\" name=\"paymentSum\" id=\"paymentS\" data-theme=\"b\" placeholder=\"Sum Payed\">\n"
                + "            <label> </label>\n"
                + "            <label>  </label>\n"
                + "            <div class=\"ui-grid-a\">"
                + "<div class=\"ui-block-a\">"
                + "<span> <a href=\"#\" data-role=\"button\" data-corners=\"true\""
                + "	data-shadow=\"true\" data-iconshadow=\"true\" data-wrapperels=\"span\""
                + "	data-theme=\"b\""
                + "	class=\"ui-btn ui-shadow ui-btn-corner-all ui-btn-icon-left ui-btn-up-c ui-btn-b ui-icon-plus ui-nodisc-icon\" "
                + "onclick=\"addPayment()\">"
                + "<span class=\"ui-btn-inner ui-btn-corner-all\"> <span"
                + "	class=\"ui-btn-text\"> Add Expense </span> <span"
                + "	class=\"ui-icon ui-icon-shadow\">&nbsp;</span></span>"
                + "</a>"
                + "</span>"
                + "</div>"
                + "<div class=\"ui-block-b\">"
                + "	<span> <a href=\"#\" data-role=\"button\" data-corners=\"true\""
                + "		data-shadow=\"true\" data-iconshadow=\"true\" data-wrapperels=\"span\""
                + "		data-theme=\"b\""
                + "		class=\"ui-btn ui-shadow ui-btn-corner-all ui-btn-icon-left ui-btn-up-c ui-btn-b ui-icon-delete ui-nodisc-icon\""
                + "				 onclick=\"cancelPaymentAddition()\">"
                + "			<span class=\"ui-btn-inner ui-btn-corner-all\"> <span"
                + "				class=\"ui-btn-text\">Cancel </span> <span"
                + "				class=\"ui-icon ui-icon-delete ui-icon-shadow\">&nbsp;</span></span>"
                + "	</a>" + "	</span>" + "</div>" + "</div>" + "</div>";

        return Response.ok().entity(content).build();

    }
}
