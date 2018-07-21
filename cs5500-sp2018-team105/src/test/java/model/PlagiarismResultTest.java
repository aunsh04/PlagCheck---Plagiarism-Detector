package model;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


/**
 * This is a test class for the PlagiarismResult POJO
 * @author  Aunsh Chaudhari
 */
public class PlagiarismResultTest {
	

    /**
     * Checks id of the first student of plagiarism result through setter and getter
     * 
     */
	@Test
	public void testStudentId1() {
		PlagiarismResult p = new PlagiarismResult();
		p.setStudentId1("student-100");
		assertEquals("student-100",p.getStudentId1());
    	
	}
	
    /**
     * Checks id of second studentId of plagiarism result through setter and getter
     * 
     */
	@Test
	public void testStudentId2() {
		PlagiarismResult p = new PlagiarismResult();
		p.setStudentId2("student-125");
		assertEquals("student-125",p.getStudentId2());
    	
	}
	
    /**
     * Checks hwId of plagiarism result through setter and getter
     * 
     */
	@Test
	public void testHwId() {
		PlagiarismResult p = new PlagiarismResult();
		p.setHwId("hw1");
		assertEquals("hw1",p.getHwId());
    	
	}
	
	
    /**
     * Checks percentage of first student's submission in plagiarism result through setter and getter
     * 
     */
	@Test
	public void testPercentage1() {
		PlagiarismResult p = new PlagiarismResult();
		p.setPercentage1(80.55);
		assertEquals(80.5566,p.getPercentage1(),0.01);
    	
	}
	
	
    /**
     * Checks percentage of second student's submission in plagiarism result through setter and getter
     * 
     */
	@Test
	public void testPercentage2() {
		PlagiarismResult p = new PlagiarismResult();
		p.setPercentage2(8.15);
		assertEquals(8.15,p.getPercentage2(),0.01);
    	
	}
	
	
	
    /**
     * Checks file of first student's submission in plagiarism result through setter and getter
     * 
     */
	@Test
	public void testFile1() {
		PlagiarismResult p = new PlagiarismResult();
		p.setFile1(new File("src/main/resources/3sum.py"));
		assertEquals(new File("src/main/resources/3sum.py"),p.getFile1());
    	
	}
	
	
    /**
     * Checks file of first student's submission in plagiarism result through setter and getter
     * 
     */
	@Test
	public void testFile2() {
		PlagiarismResult p = new PlagiarismResult();
		p.setFile2(new File("src/main/resources/4sum.py"));
		assertEquals(new File("src/main/resources/4sum.py"),p.getFile2());
    	
	}
	
	
    /**
     * Checks constructor of plagiarism result 
     * 
     */
	@Test
	public void testConstructor() {
		List<Integer> lines=new ArrayList<>();
		SimilarLines similarLines=new SimilarLines(lines,lines);
		PlagiarismResult p = new PlagiarismResult("student-100","student-125","hw1",new File("src/main/resources/3sum.py"),new File("src/main/resources/4sum.py"),80.55,8.15,similarLines);
		assertEquals(new File("src/main/resources/4sum.py"),p.getFile2());
    	
	}
}

