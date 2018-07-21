/**
 * 
 * @author Nirupama
 * Object of this class represents TeachingAssistant
 * personID represents the TA's ID
 * username represents the TA's username
 * password represents the TA's password
 * firstName represents the TA's first name
 * secondName represents the TA's second name
 * email represents the TA's email address
 *
 */

public class TeachingAssistant extends Instructor{
    private int personId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    
    /*
     * represents the constructor of TeachingAssistant
     */

    public TeachingAssistant() {

    }
    @Override
    public boolean login() {
        return true;
    }


}
