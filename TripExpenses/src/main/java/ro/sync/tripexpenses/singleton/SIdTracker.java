/**
 * 
 */
package ro.sync.tripexpenses.singleton;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.LinkedList;

/**
 * @author Mihai.
 *
 */
public class SIdTracker {
    
    private static SIdTracker instance = null;
    
    private static LinkedList<String> sIdList = null;
    private SecureRandom random = new SecureRandom();
    /**
     * to prevent instantiation.
     */
    private SIdTracker (){
        sIdList = new LinkedList<String>();
    };

    /**
     * Creates a singleton instance of the SingletonSIdTracker class.
     * @return the unique instance.
     */
    public static SIdTracker getInstance () {
        if (instance == null) {
            instance = new SIdTracker ();
        } 
        return instance;
    }
    
    /**
     * Generates a unique random secured session Id.
     * @return a unique session id. 
     */
    public String generateSId () {
        int marker = 1;
        String sid = null;
        while(marker == 1) {
            sid = new BigInteger(130, random).toString(32);
            if(!sIdList.contains(sid)) {
                marker = 0;
                sIdList.add(sid);
            }
        }
        return sid;
    }
    
    /**
     * Removes a sid from the list.
     * @param sid 
     *          The sid to be removed.
     */
    public void removeSId(String sid) {
        sIdList.remove(sid);
    }
}
