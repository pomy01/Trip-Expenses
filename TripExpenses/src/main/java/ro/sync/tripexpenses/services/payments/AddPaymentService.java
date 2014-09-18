package ro.sync.tripexpenses.services.payments;

import java.util.Collection;
import java.util.Date;

import javax.ws.rs.CookieParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.hibernate.Query;
import org.hibernate.Session;

import ro.sync.tripexpenses.singleton.SingletonSessionFactory;
import ro.sync.tripexpenses.tables.Payment;
import ro.sync.tripexpenses.tables.Trip;
import ro.sync.tripexpenses.tables.UserRegistered;
import ro.sync.tripexpenses.tables.UserTrip;

/**
 * A service for creating a new payment.
 * 
 * @author Mihai.
 * 
 */
@Path("/AddPaymentService")
public class AddPaymentService {
    /**
     * @param sid 
     *             The session cookie of the user.
     * @param tripCredential the credential to identify the trip.
     * @param paymentDescription 
     *             The new payment description
     * @param paymentSum 
     *             The amount of money payed to cover the expense.
     * @return a response.
     */
    @POST
    @Produces("text/plain")
    public Response addNewPayment(@CookieParam(value = "sid") String sid,
            @QueryParam("tripCredential") String tripCredential,
            @QueryParam("paymentDescription") String paymentDescription,
            @QueryParam("paymentSum") int paymentSum) {
        
        UserTrip userTrip = null;
        UserRegistered user = null;
        Trip trip = null;
        Payment newPayment = null;
        String responseString = "";
        System.out.println("\n\n\n begin payment insertion");
        Session hiberSession = SingletonSessionFactory.getNewSession();
        try {
            hiberSession.beginTransaction();
            Query userQuery = hiberSession
                    .createQuery("from UserRegistered where sessionID='" + sid
                            + "'");
            user = (UserRegistered)userQuery.uniqueResult();
            Query userTripQuery = hiberSession
                    .createQuery("from UserTrip where user_id='" + user.getUserID()
                            + "' and trip_id='" + tripCredential + "'");
            userTrip = (UserTrip)userTripQuery.uniqueResult();
            newPayment = new Payment();
            newPayment.setPaymentDate(new Date());
            newPayment.setPaymentDescription(paymentDescription);
            newPayment.setSum(paymentSum);
            if( (user == null) || (userTrip == null)) {
                hiberSession.save(newPayment);
                
                hiberSession.getTransaction().commit();
                hiberSession.close();
                responseString = "CREATION_FAILED";
                return Response.ok().entity(responseString).build();
            }
            Collection<Payment> paymentsDone = user.getPaymentsDone();
            paymentsDone.add(newPayment);
            user.setPaymentsDone(paymentsDone);
            
            trip = userTrip.getTrip();
            paymentsDone = trip.getPaymentsList();
            paymentsDone.add(newPayment);
            trip.setPaymentsList(paymentsDone);
            
            newPayment.setPaymentUser(userTrip.getUser());                        
            newPayment.setPaymentTrip(userTrip.getTrip());
            
            hiberSession.save(newPayment);
            
            responseString = "ADDED_SUCCESFULLY";
        } catch (Exception ex) {
            System.out.println("AddTripService database access failed\n\n\n\n");
            ex.printStackTrace();
        }
        System.out.println("\n\n\n finished introducing the payment.");
        hiberSession.getTransaction().commit();
        hiberSession.clear();
        hiberSession.close();
        return Response.ok().entity(responseString).build();
    }
}


