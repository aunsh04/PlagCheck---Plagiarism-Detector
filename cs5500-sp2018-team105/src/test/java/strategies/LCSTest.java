package strategies;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import model.PlagiarismResult;
import model.Submission;
import strategies.LCS;

/**
 * This is a test class to test and check the similarity between files 
 * with the help of the Longest Common subsequence string comparison algorithm
 * @author  Aunsh Chaudhari
 */
public class LCSTest {
	
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
        LCS lcs = new LCS();
        List<PlagiarismResult> result = lcs.getReport(list);
        assertEquals(100.0,result.get(0).getPercentage1(),0.01);
	}
	
	/**
     * This is a test to check the similarity percent between multiple
     * files which have the student id
     */
	@Test
	public void testSameStudentId() {
    	List<Submission> list=new ArrayList<>();
        Submission sub1=new Submission("student-125","hw1",new File("src/main/resources/add-binary.py"));
        Submission sub2=new Submission("student-125","hw1",new File("src/main/resources/add-strings.py"));
        list.add(sub1);
        list.add(sub2);
        LCS lcs = new LCS();
        List<PlagiarismResult> result = lcs.getReport(list);
        assertEquals(0,result.size());
	}
	
	/**
     * This is a test to check the similarity percent between two
     * submission files that are considerably different and have a low
     * plagiarism percent
     */
	@Test
	public void testDifferentFileSubmissions() {
    	List<Submission> list=new ArrayList<>();
        Submission sub1=new Submission("student-175","hw1",new File("src/main/resources/3sum.py"));
        Submission sub2=new Submission("student-147","hw1",new File("src/main/resources/add-binary.py"));
        list.add(sub1);
        list.add(sub2);
        LCS lcs = new LCS();
        List<PlagiarismResult> result = lcs.getReport(list);
        assertEquals(24.86,result.get(0).getPercentage1(),0.01);
	}
	
	
	/**
     * This is a test to check the similarity percent when
     * an empty list of submissions is passed - no files to be compared
     */
	@Test
	public void testNoSubmissions() {
		List<Submission> list=new ArrayList<>();
		LCS lcs = new LCS();
		List<PlagiarismResult> result = lcs.getReport(list);
		assertEquals(0,result.size());
	}
	
	/**
     * This is a test to check the similarity percent when
     * multiple submissions are to be compared for plagiarism
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
        LCS lcs = new LCS();
        List<PlagiarismResult> results = lcs.getReport(submissionList);
        assertEquals(100.0,results.get(2).getPercentage1(),0.01);
        assertEquals(63.10,results.get(4).getPercentage1(),0.01);		
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
        LCS lcs = new LCS();
        submissionList.add(submission1);
        submissionList.add(submission2);
        List<PlagiarismResult> results = lcs.getReport(submissionList);
        assertEquals(100.0,results.get(0).getPercentage1(),0.01);
        assertEquals(100.0,results.get(0).getPercentage2(),0.01);	
        
	}
	
	
	
	
}

