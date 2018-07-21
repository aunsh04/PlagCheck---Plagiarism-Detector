import java.util.ArrayList;
import java.util.List;

/*
 * Object of this class represents the object of UserAccount
 * personID represents the user's ID
 * newTest represents the new test to be run
 */

public class UserAccount {
    private String personId;
    protected NewTest newTest;
    
    /*
     * represents the constructor of UserAccount
     */
    public UserAccount() {

    }


    public void runNewTest() {

    }

    public List<Report> viewReports() {
        List<Report> reportList = new ArrayList<>();

        // code to retrieve generated reports in the page

        return reportList;
    }
}
