/**
 * 
 * @author Nirupama
 * Object of this class represents Professor
 * personID represents the professor's ID
 * username represents the professor's username
 * password represents the professor's password
 * firstName represents the professor's first name
 * secondName represents the professor's second name
 * email represents the professor's email address
 *
 */

public class Professor extends Instructor{
    private int personId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    /*
     * represents the constructor for Professor
     */
    public Professor() {

    }

    @Override
    public boolean login() {
        return true;
    }
}
