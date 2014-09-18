/**
 * 
 */
package ro.sync.tripexpenses.tables;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author user
 */
@Entity
@Table(name = "Trips")
public class Trip implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int tripID;
    
    private boolean closed;
    private String tripResolution;
    
    private String tripLocation;
    private String tripDescription;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userTripId.trip", cascade=CascadeType.ALL)
    private Collection <UserTrip> userTrip = new ArrayList<UserTrip>();
    
    @OneToMany
    @JoinTable(joinColumns = @JoinColumn(name = "USER_ID"),
    inverseJoinColumns = @JoinColumn(name = "PAYMENT_ID"))
    private Collection<Payment> paymentsList = new ArrayList<Payment>();

    private String name;
    
    @ManyToOne
    private UserRegistered creator;

    /**
     * @return the tripName
     */
    public String getName() {
        return name;
    }

    /**
     * @param tripName the tripName to set
     */
    public void setName(String tripName) {        
        this.name = tripName;
    }

    /**
     * @return the creatorUser
     */
    public UserRegistered getCreator() {
        return creator;
    }

    /**
     * @param creatorUser the creatorUser to set
     */
    public void setCreator(UserRegistered creatorUser) {
        this.creator = creatorUser;
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
     * @return the paymentsList
     */
    public Collection<Payment> getPaymentsList() {
        return paymentsList;
    }

    /**
     * @param paymentsList the paymentsList to set
     */
    public void setPaymentsList(Collection<Payment> paymentsList) {
        this.paymentsList = paymentsList;
    }
    /**
     * Getter for the trip Id.
     * @return the trip Id.
     */
    public String getTripID () {
        return Integer.toString(this.tripID);
    }

	/**
	 * @return the tripLocation
	 */
	public String getTripLocation() {
		return tripLocation;
	}

	/**
	 * @param tripLocation the tripLocation to set
	 */
	public void setTripLocation(String tripLocation) {
		this.tripLocation = tripLocation;
	}

	/**
	 * @return the tripDescription
	 */
	public String getTripDescription() {
		return tripDescription;
	}

	/**
	 * @param tripDescription the tripDescription to set
	 */
	public void setTripDescription(String tripDescription) {
		this.tripDescription = tripDescription;
	}

    /**
     * @param tripID the tripID to set
     */
    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

    /**
     * @return the closed
     */
    public boolean isClosed() {
        return closed;
    }

    /**
     * @param closed the closed to set
     */
    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    /**
     * @return the tripResolution
     */
    public String getTripResolution() {
        return tripResolution;
    }

    /**
     * @param tripResolution the tripResolution to set
     */
    public void setTripResolution(String tripResolution) {
        this.tripResolution = tripResolution;
    }
    
}
