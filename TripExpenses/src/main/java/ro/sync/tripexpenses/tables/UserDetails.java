/**
 * 
 */
package ro.sync.tripexpenses.tables;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * Holds the primary details of the user.
 * @author user
 *
 */
@Embeddable
public class UserDetails {

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int detailsID;
    
    private String userName;
    private String emailAdress;
    
    @ManyToMany(mappedBy="userFriends")
    private Collection <UserRegistered> userz = new ArrayList<UserRegistered>();
    
    /**
     * @param name
     * @param email
     */
    public UserDetails (String name, String email) {
        this.userName = name;
        this.emailAdress = email;
    }
    /**
     * Default constructor.
     * Initializes the userName and emailAdress with empty strings.
     */
    public UserDetails () {
        this("", "");
    }
    /**
     * @return the user
     */
    public String getUserName() {
        return userName;
    }
    /**
     * @param user the user to set
     */
    public void setUserName(String user) {
        this.userName = user;
    }
    /**
     * @return the emailAdress
     */
    public String getEmailAdress() {
        return emailAdress;
    }
    /**
     * @param emailAdress the emailAdress to set
     */
    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }
    /**
     * @return the userz
     */
    public Collection <UserRegistered> getUserz() {
        return userz;
    }
    /**
     * @param userz the userz to set
     */
    public void setUserz(Collection <UserRegistered> userz) {
        this.userz = userz;
    }

}
