package strategies;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import model.PlagiarismResult;
import model.Submission;
/**
 * Test class for antlr comparison strategy
 * @author Nirupama
 *
 */
public class ASTStrategyTest {
	private static double EPSILON=0.01;
	/**
	 * Test case for generating report for two submissions
	 */
	@Test
	public void testTwoSubmissions() {
		List<Submission> list=new ArrayList<>();
		Submission sub1=new Submission("student-125","hw1",new File("src/main/resources/3sum.py"));
		Submission sub2=new Submission("student-126","hw1",new File("src/main/resources/3sum.py"));
		list.add(sub1);
		list.add(sub2);
		ASTStrategy ast=new ASTStrategy();
		List<PlagiarismResult> result=ast.getReport(list);
		assertEquals(99.34, result.get(0).getPercentage1(),EPSILON);
	}
	
	/**
	 * Test case for generating report for two submissions of same student
	 */
	@Test
	public void testTwoSubmissionsofSameStudent() {
		List<Submission> list=new ArrayList<>();
		Submission sub1=new Submission("student-126","hw1",new File("src/main/resources/3sum.py"));
		Submission sub2=new Submission("student-126","hw1",new File("src/main/resources/3sum.py"));
		list.add(sub1);
		list.add(sub2);
		ASTStrategy ast=new ASTStrategy();
		List<PlagiarismResult> expectedResult=new ArrayList<>();
		List<PlagiarismResult> result=ast.getReport(list);
		assertEquals(expectedResult, result);
	}
	/**
	 * Test case for generating report for two submissions with no files
	 */
	@Test
	public void testSubmissionswithNofile() {
		List<Submission> list=new ArrayList<>();
		Submission sub1=new Submission("student-125","hw1",null);
		Submission sub2=new Submission("student-126","hw1",null);
		list.add(sub1);
		list.add(sub2);
		ASTStrategy ast=new ASTStrategy();
		List<PlagiarismResult> result=ast.getReport(list);
		assertEquals(0, result.get(0).getPercentage1(),EPSILON);
	}
	/**
	 * Test case for generating report for multiple submissions not checking files of similar student
	 */
	@Test
	public void testMultipleSubmissions() {
		List<Submission> list=new ArrayList<>();
		Submission sub1=new Submission("student-125","hw1",new File("src/main/resources/3sum.py"));
		Submission sub2=new Submission("student-126","hw1",new File("src/main/resources/3sum1.py"));
		Submission sub3=new Submission("student-125","hw1",new File("src/main/resources/coin-path.py"));
		Submission sub4=new Submission("student-126","hw1",new File("src/main/resources/coin-change.py"));
		list.add(sub1);
		list.add(sub2);
		list.add(sub3);
		list.add(sub4);
		ASTStrategy ast=new ASTStrategy();
		List<PlagiarismResult> result=ast.getReport(list);
		assertEquals(99.34, result.get(0).getPercentage1(),EPSILON);
		assertEquals(99.34, result.get(0).getPercentage2(),EPSILON);
		assertEquals(7.89, result.get(1).getPercentage1(),EPSILON);
		assertEquals(34.61, result.get(1).getPercentage2(),EPSILON);
		assertEquals(12.5, result.get(2).getPercentage1(),EPSILON);
		assertEquals(28.64, result.get(2).getPercentage2(),EPSILON);
		assertEquals(14.57, result.get(3).getPercentage1(),EPSILON);
		assertEquals(27.88, result.get(3).getPercentage2(),EPSILON);
	}
	/**
	 * Test case for generating report for multiple submissions not checking files of similar student
	 */
	@Test
	public void testMultipleSubmissionsWithDifferentStudents() {
		List<Submission> list=new ArrayList<>();
		Submission sub1=new Submission("student-125","hw1",new File("src/main/resources/3sum.py"));
		Submission sub2=new Submission("student-126","hw1",new File("src/main/resources/3sum1.py"));
		Submission sub3=new Submission("student-127","hw1",new File("src/main/resources/coin-path.py"));
		list.add(sub1);
		list.add(sub2);
		list.add(sub3);
		ASTStrategy ast=new ASTStrategy();
		List<PlagiarismResult> result=ast.getReport(list);
		assertEquals(99.34, result.get(0).getPercentage1(),EPSILON);
		assertEquals(99.34, result.get(0).getPercentage2(),EPSILON);
		assertEquals(12.5, result.get(1).getPercentage1(),EPSILON);
		assertEquals(28.64, result.get(1).getPercentage2(),EPSILON);
		assertEquals(12.5, result.get(2).getPercentage1(),EPSILON);
		assertEquals(28.64, result.get(2).getPercentage2(),EPSILON);

	}


}