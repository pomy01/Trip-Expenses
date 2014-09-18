package ro.sync.tripexpenses.services;


import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.hibernate.Query;
import org.hibernate.Session;

import ro.sync.tripexpenses.singleton.SingletonSessionFactory;
import ro.sync.tripexpenses.tables.UserRegistered;

/**
 * A service for registering users.
 * @author Pomy.
 *
 */
@Path("/RegisterService")
public class RegisterService {
    /**
     * 
     * @param username
     * @param password
     * @param mail
     * @return a rescponse.
     */
    @POST
    public Response register(@QueryParam("username") String username,
            @QueryParam("password") String password,
            @QueryParam("mail") String mail
            ) {
        Session hiberSession = SingletonSessionFactory.getNewSession();
        hiberSession.beginTransaction();
        
        try {
            Query query = hiberSession.createQuery("from UserRegistered where userEmail='" + mail
                    + "'");
            System.out.println("after query creation and before requesting unique result");
            UserRegistered user = (UserRegistered) query.uniqueResult();
            System.out.println("after unique result");
            if (user == null) {
                UserRegistered newUser = new UserRegistered();
                System.out.println("the user was not found \n and it will be added to the database");
                newUser.setUserName(username);
                newUser.setEmailAdress(mail);
                newUser.setUserPassword(password);
                
                hiberSession.save(newUser);
                hiberSession.getTransaction().commit();
                hiberSession.clear();
                hiberSession.close();
            }else {
                hiberSession.getTransaction().commit();
                hiberSession.close();
            }
        }catch (Exception ex) {
        }finally {
            System.out.println("registration process finished");
        }
        return Response
                .ok()
                .entity("Userul cu " + username + " emailul " + mail
                        + " password " + password).build();
    }
  }
