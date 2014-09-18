/**
 * 
 */
package ro.sync.tripexpenses.tables;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author user
 *
 */
@Entity
@Table(name = "User_Trips")
@AssociationOverrides({
        @AssociationOverride(name = "userTripId.user", joinColumns = @JoinColumn(name = "USER_ID")),
        @AssociationOverride(name = "userTripId.trip", joinColumns = @JoinColumn(name = "TRIP_ID")) })
public class UserTrip implements java.io.Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private UserTripId userTripId ;
    //the extra column in the join table
    @Column (name = "JOINED", nullable = false)    
    private boolean joinedTrip = false;
    
    /**
     * Constructor.
     */
    public UserTrip () {
        userTripId = new UserTripId();
    }
    /**
     * Constructor.
     * @param user the user.
     * @param trip the trip to be set.
     */
    public UserTrip (UserRegistered user, Trip trip) {
        userTripId = new UserTripId();
        trip.setCreator(user);
        userTripId.setTrip(trip);
        userTripId.setUser(user);
    }
    
    
    /**
     * @return the user.
     */
     @Transient
    public UserRegistered getUser() {
        return userTripId.getUser();
    }
    
    /**
     * @param userTripId the userTripId to set
     */
    public void setUserTripId(UserTripId userTripId) {
        this.userTripId = userTripId;
    }

    /**
     * Sets the user.
     * @param user
     */
    public void setUser(UserRegistered user) {
        userTripId.setUser(user);
    }
    /**
     * 
     * @return the Trip.
     */
    @Transient
    public Trip getTrip() {
        return userTripId.getTrip();
    }
    /**
     * Sets the Trip.
     * @param trip
     */
    public void setTrip( Trip trip) {
        userTripId.setTrip(trip);
    }
    /**
     * @return the userTripId
     */
    @EmbeddedId
    public UserTripId getUserTripId() {
        return userTripId;
    }

    /**
     * @return the joinedTrip
     */
    public boolean isJoinedTrip() {
        return joinedTrip;
    }
    /**
     * @param joinedTrip the joinedTrip to set
     */
    public void setJoinedTrip(boolean joinedTrip) {
        this.joinedTrip = joinedTrip;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        UserTrip that = (UserTrip) o;

        if (getUserTripId() != null ? !getUserTripId().equals(that.getUserTripId())
                : that.getUserTripId() != null)
            return false;

        return true;
    }
    
    @Override
    public int hashCode() {
        return (getUserTripId() != null ? getUserTripId().hashCode() : 0);
    }

}
