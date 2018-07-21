package model;
import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;


/**
 * This is a test class for the Submission POJO
 * @author  Aunsh Chaudhari
 */
public class SubmissionTest {
	

    /**
     * Checks student id of submission through setter and getter
     * 
     */
	@Test
	public void testStudentId() {
		Submission s = new Submission();
		s.setStudentId("student-100");
		assertEquals("student-100",s.getStudentId());
    	
	}
	
	
    /**
     * Checks hw id of submission through setter and getter
     * 
     */
	@Test
	public void testHwId() {
		Submission s = new Submission();
		s.setHwId("hw1");
		assertEquals("hw1",s.getHwId());
    	
	}
	
    /**
     * Checks file of submission through setter and getter
     * 
     */
	@Test
	public void testFile() {
		Submission s = new Submission();
		s.setFile(new File("src/main/resources/3sum.py"));
		assertEquals(new File("src/main/resources/3sum.py"),s.getFile());
    	
	}
	
	
    /**
     * Checks constructor of submission
     * 
     */
	@Test
	public void testConstructor() {
		Submission s = new Submission("student-100","hw1",new File("src/main/resources/3sum.py"));
		assertEquals(new File("src/main/resources/3sum.py"),s.getFile());
    	
	}
	
}

