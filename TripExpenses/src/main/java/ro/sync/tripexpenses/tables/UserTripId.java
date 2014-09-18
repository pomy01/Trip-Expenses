/**
 * 
 */
package ro.sync.tripexpenses.tables;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
/**
 * @author Mihai
 *
 */
@Embeddable
public class UserTripId implements java.io.Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    private int id;
    @Column(nullable = false)
    private UserRegistered user;
    @Column(nullable = false)
    private Trip trip;

    /**
     * @return the user
     */
    @ManyToOne
    public UserRegistered getUser() {
        return user;
    }
    /**
     * @param user the user to set
     */
    public void setUser(UserRegistered user) {
        this.user = user;
    }
    /**
     * @return the trip
     */
    @ManyToOne
    public Trip getTrip() {
        return trip;
    }
    /**
     * @param trip the trip to set
     */
    public void setTrip(Trip trip) {
        this.trip = trip;
    }
    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTripId that = (UserTripId) o;

        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (trip != null ? !trip.equals(that.trip) : that.trip != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (user != null ? user.hashCode() : 0);
        result = 31 * result + (trip != null ? trip.hashCode() : 0);
        return result;
    }
}
