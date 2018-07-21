package strategies;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import model.PlagiarismResult;
import model.Submission;

/**
 * This class tests the Weighted Polynomial Strategy
 *
 * @author  Aunsh Chaudhari
 */

public class WeightedPolynomialStrategyTest {

	/**
     * This is a test to check the similarity percent for the weighted polynomial strategy between two
     * files which are an exact match
     */
	@Test
	public void testExactMatch() {
    	List<Submission> list=new ArrayList<>();
        Submission sub1=new Submission("student-125","hw1",new File("src/main/resources/3sum.py"));
        Submission sub2=new Submission("student-126","hw1",new File("src/main/resources/3sum.py"));
        list.add(sub1);
        list.add(sub2);
        WeightedPolynomialStrategy weighted = new WeightedPolynomialStrategy();
        List<PlagiarismResult> results = weighted.getReport(list);
        assertEquals(99.67,results.get(0).getPercentage1(),0.01);
	}
	
	/**
     * This is a test to check the similarity percent for the weighted polynomial strategy between two
     * files that are not plagiarized
     */
	@Test
	public void testDifferentFiles() {
    	List<Submission> list=new ArrayList<>();
        Submission sub1=new Submission("student-100","hw1",new File("src/main/resources/add-binary.py"));
        Submission sub2=new Submission("student-105","hw1",new File("src/main/resources/coin-change.py"));
        list.add(sub1);
        list.add(sub2);
        WeightedPolynomialStrategy weighted = new WeightedPolynomialStrategy();
        List<PlagiarismResult> results = weighted.getReport(list);
        assertEquals(13.00,results.get(0).getPercentage1(),0.01);
        assertEquals(8.64,results.get(0).getPercentage2(),0.01);
	}
	
}
