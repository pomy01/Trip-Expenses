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
import ro.sync.tripexpenses.tables.*;

/**
 * A that generates the HTML content of the trip list.
 * 
 * @author Mihai.
 */
@Path("/DisplayTripService")
public class DisplayTripService {

    /**
     * Post method DisplayTripService.
     * 
     * @param sid
     *            The sessionID cookie of the user making the request.
     * @param tripCred
     *            The trip identification credential.
     * @return the trip's html content.
     */
    @SuppressWarnings("deprecation")
    @POST
    @Produces("text/plain")
    public Response displayTripDetails(@CookieParam(value = "sid") String sid,
            @QueryParam("tripCred") String tripCred) {
        
        //TODO: break code in several functions where possible to increase code readability.
        String content;
        
        content = "<div id=\"tripInfo\">" + "<div class=\"ui-grid-a\" >"
                + "<div class=\"ui-block-a\">";

        Collection<Payment> payments = null;
        Trip trip = null;
        UserRegistered user = null;
        
        float totalSum = 0;

        Session hiberSession = SingletonSessionFactory.getNewSession();
        try {
            hiberSession.beginTransaction();
            Query query2 = hiberSession
                    .createQuery("from UserRegistered where sessionID='" + sid + "'");
            user = (UserRegistered) query2.uniqueResult();

            Collection<UserTrip> userTrips = user.getUserTrip();
            for (UserTrip userTrip : userTrips) {
                if (userTrip.getTrip().getTripID().compareTo(tripCred) == 0) {
                    trip = userTrip.getTrip();
                    content += "	<span> <a href=\"#\" data-role=\"button\" "
                            + "		            data-corners=\"true\" data-shadow=\"true\""
                            + "		            data-iconshadow=\"true\" data-wrapperels=\"span\" data-theme=\"b\""
                            + "		            class=\"ui-btn ui-shadow ui-btn-corner-all ui-btn-icon-left "
                            + "                 ui-btn-up-c ui-btn-b ui-icon-home ui-nodisc-icon\">"
                            + "				<span class=\"ui-btn-inner ui-btn-corner-all\">"
                            + "				  <span class=\"ui-btn-text\"> "
                            + trip.getName()
                            + "                </span> "
                            + "				<span class=\"ui-icon ui-icon-delete ui-icon-shadow\">&nbsp;</span></span></a>"
                            + "	    </span>"
                            + "	</div>"
                            + "	 <div class=\"ui-block-b\">"
                            + "	     <span> "
                            + "          <a href=\"#\" data-role=\"button\""
                            + "		        data-corners=\"true\" data-shadow=\"true\""
                            + "		        data-iconshadow=\"true\" data-wrapperels=\"span\" data-theme=\"b\""
                            + "		        class=\"ui-btn ui-shadow ui-btn-corner-all ui-btn-icon-left ui-btn-up-c ui-btn-b ui-icon-location ui-nodisc-icon\">"
                            + "              <span class=\"ui-btn-inner ui-btn-corner-all\">"
                            + "				<span class=\"ui-btn-text\">"
                            + trip.getTripLocation()
                            + "              </span>"
                            + "				<span class=\"ui-icon ui-icon-delete ui-icon-shadow\">&nbsp;</span></span></a>"
                            + "	</span></div></div></div>"
                            + ""
                            + "<div id=\"membersWindow\">"
      //TODO: the add member button it would be a better idea to show it only to the creator user.
                            + "<div class=\"ui-grid-a\" >"
                            + "<div class=\"ui-block-a\">"
                            + "<a href=\"#\" onclick=\"displayMembers()\""
                            + "class=\"ui-btn ui-shadow ui-btn-corner-all ui-btn-icon-left ui-btn-up-c"
                            + " ui-btn-b ui-icon-user ui-nodisc-icon\" data-theme=\'b\'>Members</a>"
                            + "</div>"
                            + "  <div class=\"ui-block-b\">"
                            + "<a href=\"#\" onclick=\"addMemberForm()\""
                            + "class=\"ui-btn ui-shadow ui-btn-corner-all ui-btn-icon-left ui-btn-up-c"
                            + " ui-btn-b ui-icon-plus ui-nodisc-icon\" data-theme=\'b\'>Add Member</a>"
                            + "</div></div>";
     //TODO: this section should be hidden and reveal on button click.
                    UserRegistered creator = trip.getCreator();
                    content += "<ul id=\"membersList\"data-role='listview' data-inset='true'\n"
                            + "data-filter=\"true\" data-input=\"#myFilter\" data-inset=\"true\" data-theme='a'>\n"
                            + "<li data-theme='b'><a href='#' data-theme='b'>"
                            + creator.getUserName() + "</a></li>\n";
                    for (UserTrip userTripIterator : trip.getUserTrip()) {
                        UserRegistered member = userTripIterator.getUser();
                        if (!member.equals(creator)) {
                            content += "<li><a href='#'>"
                                    + member.getUserName() + "</a></li>\n";
                        }
                    }
                    content += "</ul></div>" 
                            + "<div id=\"paymentsWindow\">";
                    payments = trip.getPaymentsList();

                        content += "<a href=\"#\" onclick=\"addPaymentForm()\""
                                + "class=\"ui-btn ui-shadow ui-btn-corner-all ui-btn-icon-left "
                                + "ui-btn-up-c ui-btn-b ui-icon-plus ui-nodisc-icon\" data-theme=\'b\'>Add Expense</a>"
                                + "<ul id=\"paymentsList\"data-role='listview' data-theme='b'>\n";
                     if (!trip.isClosed()) {
                        if (!payments.isEmpty()) {
                            totalSum = 0;
                            for (Payment payment : payments) {
                                content += "<li data-name='"
                                        + payment.getSum()
                                        + "'><a href='#' onclick=\"paymentSelected(this); return false;\"><b>    "
                                        + payment.getPaymentUser()
                                                .getUserName() + " payed </b>"
                                        + payment.getSum() + " <b>for </b>"
                                        + payment.getPaymentDescription()
                                        + "<b>  at </b>"
                                        + payment.getPaymentDate().getHours()
                                        + " : "
                                        + payment.getPaymentDate().getMinutes()
                                        + "</a></li>\n";
                                totalSum += payment.getSum();
                            }
                            float sumAverage;
                            sumAverage = totalSum / trip.getUserTrip().size();
                            content += "<li data-theme='a'><a href='#' data-theme='a'>    "
                                    + "Total Sum :"
                                    + totalSum
                                    + " ("
                                    + sumAverage
                                    + " ) per participant"
                                    + "</a></li>";
                        }else {
                            content += "<li><a href='#' onclick=\"addPayment(); return false;\">    "
                                    + " No Payments so far...</a></li>"
                                    + "<li data-theme='a'><a href='#' data-theme='a'>    "
                                    + "Total Sum :" + 0.0 + "</a></li>";
                        }
                        //if the user is the creator of the trip he can close the trip.
                        if ( trip.getCreator().equals(user)){
                            content += "<li><a href='#' onclick=\"closeTrip(); return false;\" data-role=\"button\""
                                    + "data-corners=\"true\" data-shadow=\"true\""
                                    + "             data-iconshadow=\"true\" data-wrapperels=\"span\" data-theme=\"b\""
                                    + "             class=\"ui-btn ui-shadow ui-btn-corner-all ui-btn-icon-left ui-btn-up-c ui-btn-b ui-icon-delete ui-nodisc-icon\">"
                                    + "Close trip</a></li>";
                        }
                    } else { //the trip is closed
                        float payedSum = 0;
                        UserRegistered member = null;
                        totalSum = 0;
                        for(Payment payment : trip.getPaymentsList()) {
                            totalSum+= payment.getSum();
                        }
                        for(UserTrip memberTrip : trip.getUserTrip()) {
                            member = memberTrip.getUserTripId().getUser();
                            payedSum = 0;
                            for(Payment payment : trip.getPaymentsList()) {
                                if(payment.getPaymentUser().equals(member)) {
                                    payedSum += payment.getSum();
                                }
                            }
                            content += "<li><a href='#' data-role=\"button\""
                                    + "data-corners=\"true\" data-shadow=\"true\""
                                    + "             data-iconshadow=\"true\" data-wrapperels=\"span\" data-theme=\"b\""
                                    + "             class=\"ui-btn ui-shadow ui-btn-corner-all ui-btn-icon-left ui-btn-up-c ui-btn-b ui-icon-plus ui-nodisc-icon\">"
                                    + member.getUserName() + " ";
                            float averageSum = totalSum / trip.getUserTrip().size();
                            
                            if (payedSum > averageSum ) {
                                content += "has to receive " + (payedSum - averageSum);
                            } else {
                                content += "has to give " + (averageSum - payedSum);
                            }
                            content += "</a></li>";
                        }
                        if ( trip.getCreator().equals(user)){
                            content += "<li><a href='#' onclick=\"reopenTrip(); return false;\" data-role=\"button\""
                                    + "data-corners=\"true\" data-shadow=\"true\""
                                    + "             data-iconshadow=\"true\" data-wrapperels=\"span\" data-theme=\"b\""
                                    + "             class=\"ui-btn ui-shadow ui-btn-corner-all ui-btn-icon-left ui-btn-up-c ui-btn-b ui-icon-delete ui-nodisc-icon\">"
                                    + "Reopen Trip</a></li>";
                        }
                    }
                    break;
                }
            }
        }catch (Exception ex) {
            content.concat("<li><a href='#'>Error accesing your trips</a></li>\n");
            ex.printStackTrace();
        }
        hiberSession.getTransaction().commit();
        hiberSession.clear();
        hiberSession.close();

        content += "</ul></div></div>";
        return Response.ok().entity(content).build();
    }
}
