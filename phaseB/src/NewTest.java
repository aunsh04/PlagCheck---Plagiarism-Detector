import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Nirupama
 * Object of this class represents a NewTest
 * testName is the name of the new test
 * rootURL is the github URL
 * assignmentNumber is the assignment name/reference
 *
 */

public class NewTest {

    private String testName;
    private String rootURL;
    private String assignmentNumber;
    protected AssignmentFile assignmentFile;

    public NewTest() {

    }

    public void importData() {

    }

    public void resetTestDetails() {

    }

    public List<PlagiarismCase> calculatePlagiarism() {
        List<PlagiarismCase> caseList = new ArrayList<>();

        CalculatePlagiarism calculatePlagiarism = new CalculatePlagiarism();
        // code to retrieve list of all cases from the plagiarism test

        return caseList;
    }

    public void saveTestResults() {

    }


    public void generateReport() {

    }




}
