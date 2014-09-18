/**
 * 
 */
package ro.sync.tripexpenses.tables;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 * @author Mihai
 *
 */
@Entity
public class UserRegistered implements java.io.Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int userID;
    
    @Column(nullable = false)
    private String userPassword;
    private String sessionID;
    
    String userName;
    String userEmail;
    
    @ManyToMany
    private Collection <UserRegistered> userFriends;
    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "CreatedTrips")
    private Collection<Trip> createdTrips;
   
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userTripId.user")//, cascade=CascadeType.ALL)
    private Collection <UserTrip> userTrip;
    
    @OneToMany
    @JoinTable(joinColumns=@JoinColumn(name="USER_ID"),
    inverseJoinColumns=@JoinColumn(name="PAYMENT_ID")  )
    private Collection <Payment> paymentsDone;
    
    @OneToMany
    @JoinTable(name = "TripRequests")
    private Collection <Trip> tripRequests;
    
    /**
     * Contructor.
     */
    public UserRegistered () {
        userFriends = new ArrayList<UserRegistered>();
        createdTrips = new ArrayList<Trip>();
        userTrip = new ArrayList<UserTrip>();
        paymentsDone = new ArrayList<Payment>();
        tripRequests = new ArrayList<Trip>();
    }
    
    
    /**
     * Adds a payment done by the user and sets the payments user.
     * @param payment
     */
    public void addPayment(Payment payment){
        payment.setPaymentUser(this);
        paymentsDone.add(payment);
    }
    
    
    /**
     * @return the userName
     */
    public String getUserName() {
        //return details.getUserName();
        return userName;
    }
    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
       // details.setUserName(userName);
        this.userName = userName;
    }
    /**
     * @return the userPassword
     */
    public String getUserPassword() {
        return userPassword;
    }
    /**
     * @param userPassword the userPassword to set
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    /**
     * @return the sessionID
     */
    public String getSessionID() {
        return sessionID;
    }
    /**
     * @param sessionID the sessionID to set
     */
    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }
    /**
     * @return the emailAdress
     */
    public String getEmailAdress() {
        return userEmail;//details.getEmailAdress();
    }
    /**
     * @param emailAdress the emailAdress to set
     */
    public void setEmailAdress(String emailAdress) {
       // this.details.setEmailAdress(emailAdress);
        this.userEmail = emailAdress;
    }
    
    /**
     * @return the userFriends
     */
    public Collection<UserRegistered> getUserFriends() {
        return userFriends;
    }
    
    /**
     * Add a new friend to the user.
     * @param friend the new friend to add.
     */
    public void addFriend (UserRegistered friend) {
        getUserFriends().add(friend);
    }
    /**
     * @return the paymentsDone
     */
    public Collection<Payment> getPaymentsDone() {
        return paymentsDone;
    }
    /**
     * @param paymentsDone the paymentsDone to set
     */
    public void setPaymentsDone(Collection<Payment> paymentsDone) {
        this.paymentsDone = paymentsDone;
    }

    /**
     * @return the userTrip
     */
    public Collection<UserTrip> getUserTrip() {
        return userTrip;
    }
    
    
    /**
     * @param userTrip the userTrip to set
     */
    public void setUserTrip(Collection<UserTrip> userTrip) {
        this.userTrip = userTrip;
    }
    /**
     * Getter for the userID.
     * @return userID.
     */
    
    public int getUserID () {
        return this.userID;
    }

//	/**
//	 * @return the friendRequests
//	 */
//	public Collection <UserRegistered> getFriendRequests() {
//		return friendRequests;
//	}
//
//	/**
//	 * @param friendRequests the friendRequests to set
//	 */
//	public void setFriendRequests(Collection <UserRegistered> friendRequests) {
//		this.friendRequests = friendRequests;
//	}

	/**
	 * @param userFriends the userFriends to set
	 */
	public void setUserFriends(Collection <UserRegistered> userFriends) {
		this.userFriends = userFriends;
	}

	/**
	 * @return the createdTrips
	 */
	public Collection<Trip> getCreatedTrips() {
		return createdTrips;
	}

	/**
	 * @param createdTrips the createdTrips to set
	 */
	public void setCreatedTrips(Collection<Trip> createdTrips) {
		this.createdTrips = createdTrips;
	}

	/**
	 * @return the tripRequests
	 */
	public Collection <Trip> getTripRequests() {
		return tripRequests;
	}

	/**
	 * @param tripRequests the tripRequests to set
	 */
	public void setTripRequests(Collection <Trip> tripRequests) {
		this.tripRequests = tripRequests;
	}
}
