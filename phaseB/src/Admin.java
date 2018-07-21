/**
 * 
 * @author Nirupama
 * Object of this class represents Admin
 * personId represents id of admin
 * userName denotes admin username
 * firstName denotes admin firstName
 * lastName denotes admin lastName
 * email denotes admin email
 */
public class Admin implements Person{
    private int personId;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
/**
 * Constructor of Admin class
 */
    public Admin() {

    }

    @Override
    public boolean login() {
        return true;
    }

    public void setupAccounts() {

    }

}
