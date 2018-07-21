package strategies;

import static org.junit.Assert.*;

import java.io.File;


import org.junit.Test;

import model.Submission;

/**
 * This is a test class to test and check the similarity between files 
 * with the help of the Longest Common subsequence string comparison algorithm
 * @author  Aunsh Chaudhari
 */
public class AuthorStrategyTest {
	
	/**
     * This is a test to check author for two submissions when both have the same author
     */
	@Test
	public void testAuthorPresent() {
        Submission submission1=new Submission("student-175","hw1",new File("src/main/resources/testAuthorPresentMultipleFiles/3sum.py"));
        Submission submission2=new Submission("student-147","hw1",new File("src/main/resources/testAuthorPresentMultipleFiles/accounts-merge.py"));
		AuthorStrategy o = new AuthorStrategy();
		assertTrue(o.checkIfSameAuthor(submission1, submission2));
	}
	
	/**
     * This is a test to check author for two submissions when there is no author documentation in 
     * both files
     * Should return false so that check is continued for other strategies
     * 
     */
	@Test
	public void testAuthorNotPresentInBothFiles() {
        Submission submission1=new Submission("student-100","hw3",new File("src/main/resources/testAuthorNotPresentMultipleFiles/3sum.py"));
        Submission submission2=new Submission("student-109","hw3",new File("src/main/resources/testAuthorNotPresentMultipleFiles/accounts-merge.py"));
		AuthorStrategy o = new AuthorStrategy();
		assertFalse(o.checkIfSameAuthor(submission1, submission2));
	}
	
	/**
     * This is a test to check author for two submissions when there is no author documentation in 
     * one of the files
     * 
     * Should return false so that check is continued for other strategies
     */
	@Test
	public void testAuthorNotPresentInOneFiles() {
        Submission submission1=new Submission("student-100","hw3",new File("src/main/resources/testAuthorPresentInOneFile/accounts-merge.py"));
        Submission submission2=new Submission("student-109","hw3",new File("src/main/resources/testAuthorPresentInOneFile/3sum.py"));
		AuthorStrategy o = new AuthorStrategy();
		assertFalse(o.checkIfSameAuthor(submission1, submission2));
	}
	
	/**
     * This is a test to check author for two submissions when a file is not found at the destination path
     * 
     * Should throw exception and return false as file is not present
     */
	@Test
	public void testFileNotPresent() {
        Submission submission1=new Submission("student-100","hw3",new File("src/main/resources/testAuthorPresentInOneFile/4sum.py"));
        Submission submission2=new Submission("student-109","hw3",new File("src/main/resources/testAuthorPresentInOneFile/3sum.py"));
		AuthorStrategy o = new AuthorStrategy();
		assertFalse(o.checkIfSameAuthor(submission1, submission2));
	}
	
}	
