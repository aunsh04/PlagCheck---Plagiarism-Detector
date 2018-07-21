package strategies;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import model.PlagiarismResult;
import model.Submission;
import strategies.EditDistance;


/**
 * This is a test class to test and check the similarity between files 
 * with the help of the Edit Distance string comparison algorithm
 * @author  Aunsh Chaudhari
 */
public class EditDistanceTest {
	
	/**
     * This is a test to check the similarity percent between two
     * files which are an exact match
     */
	@Test
	public void testExactMatch() {
    	List<Submission> list=new ArrayList<>();
        Submission sub1=new Submission("student-125","hw1",new File("src/main/resources/3sum.py"));
        Submission sub2=new Submission("student-126","hw1",new File("src/main/resources/3sum.py"));
        list.add(sub1);
        list.add(sub2);
        EditDistance editDistance = new EditDistance();
        List<PlagiarismResult> results = editDistance.getReport(list);
        assertEquals(100.0,results.get(0).getPercentage1(),0.01);
	}
	
	/**
     * This is a test to check the similarity percent between two
     * files submitted by the same student
     */
	@Test
	public void testSameStudentId() {
    	List<Submission> list=new ArrayList<>();
        Submission sub1=new Submission("student-125","hw1",new File("src/main/resources/add-binary.py"));
        Submission sub2=new Submission("student-125","hw1",new File("src/main/resources/add-strings.py"));
        list.add(sub1);
        list.add(sub2);
        EditDistance editDistance = new EditDistance();
        List<PlagiarismResult> results = editDistance.getReport(list);
        assertEquals(0,results.size());
	}
	
	
	/**
     * This is a test to check the similarity percent between two
     * submission files where one file is a modification of another
     * 
     */
	@Test
	public void testDifferentFileSubmissions() {
    	List<Submission> list=new ArrayList<>();
        Submission sub1=new Submission("student-175","hw1",new File("src/main/resources/3sum.py"));
        Submission sub2=new Submission("student-147","hw1",new File("src/main/resources/3summodification.py"));
        list.add(sub1);
        list.add(sub2);
        EditDistance editDistance = new EditDistance();
        List<PlagiarismResult> results = editDistance.getReport(list);
        assertEquals(52.08,results.get(0).getPercentage1(),0.01);
        assertEquals(89.28,results.get(0).getPercentage2(),0.01);
	}
	
	
	/**
     * This is a test to check the similarity percent when
     * an empty list of submissions is passed - no files to be compared
     */
	@Test
	public void testNoSubmissions() {
		List<Submission> list=new ArrayList<>();
		EditDistance editDistance = new EditDistance();
		List<PlagiarismResult> result = editDistance.getReport(list);
		assertEquals(0,result.size());
	}
	
	
	/**
     * This is a test to check the similarity percent when
     * an multiple submissions are to be compared for plagiarism
     * Here, files which are similar, same and completely different have been used
     */
	@Test
	public void testMultipleSubmissions() {
		List<Submission> submissionList = new ArrayList<>();
        Submission sub1=new Submission("student-175","hw1",new File("src/main/resources/3sum.py"));
        Submission sub2=new Submission("student-147","hw1",new File("src/main/resources/add-binary.py"));
        Submission sub3=new Submission("student-100","hw1",new File("src/main/resources/add-strings.py"));
        Submission sub4=new Submission("student-95","hw1",new File("src/main/resources/3sum.py"));
        submissionList.add(sub1);
        submissionList.add(sub2);
        submissionList.add(sub3);
        submissionList.add(sub4);
        EditDistance editDistance = new EditDistance();
        List<PlagiarismResult> results = editDistance.getReport(submissionList);
        assertEquals(100.0,results.get(2).getPercentage1(),0.01);
        assertEquals(0.0,results.get(4).getPercentage1(),0.01);		
	}
	
	
	/**
     * This is a test to check the similarity percent when two submissions have the same author
     * In this case the result should have 100 percent for both the student 
     */
	@Test
	public void testSubmissionsIfSameAuthor() {
		List<Submission> submissionList = new ArrayList<>();
        Submission submission1=new Submission("student-175","hw1",new File("src/main/resources/testAuthorPresentMultipleFiles/3sum.py"));
        Submission submission2=new Submission("student-147","hw1",new File("src/main/resources/testAuthorPresentMultipleFiles/accounts-merge.py"));
        EditDistance editDistance = new EditDistance();
        submissionList.add(submission1);
        submissionList.add(submission2);
        List<PlagiarismResult> results = editDistance.getReport(submissionList);
        assertEquals(100.0,results.get(0).getPercentage1(),0.01);
        assertEquals(100.0,results.get(0).getPercentage2(),0.01);	
        
	}
}

