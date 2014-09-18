/**
 * 
 */
package ro.sync.tripexpenses.tables;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author user
 *
 */
@Entity 
public class Payment {
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int paymentID;
    @ManyToOne
    private UserRegistered paymentUser;
    @ManyToOne
    private Trip paymentTrip;
    @Column(nullable = false)
    private float sum;
    
    private String paymentDescription;
    private Date paymentDate;
    
    /**
     * @return the paymentUser
     */
    public UserRegistered getPaymentUser() {
        return paymentUser;
    }
    /**
     * @param paymentUser the paymentUser to set
     */
    public void setPaymentUser(UserRegistered paymentUser) {
        this.paymentUser = paymentUser;
    }
    /**
     * @return the paymentTrip
     */
    public Trip getPaymentTrip() {
        return paymentTrip;
    }
    /**
     * @param paymentTrip the paymentTrip to set
     */
    public void setPaymentTrip(Trip paymentTrip) {
        this.paymentTrip = paymentTrip;
    }
    /**
     * @return the sum
     */
    public float getSum() {
        return sum;
    }
    /**
     * @param sum the sum to set
     */
    public void setSum(float sum) {
        this.sum = sum;
    }
    /**
     * @return the paymentDate
     */
    public Date getPaymentDate() {
        return paymentDate;
    }
    /**
     * @param paymentDate the paymentDate to set
     */
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
    /**
     * @return the paymentDescription
     */
    public String getPaymentDescription() {
        return paymentDescription;
    }
    /**
     * @param paymentDescription the paymentDescription to set
     */
    public void setPaymentDescription(String paymentDescription) {
        this.paymentDescription = paymentDescription;
    }
    
    

}
