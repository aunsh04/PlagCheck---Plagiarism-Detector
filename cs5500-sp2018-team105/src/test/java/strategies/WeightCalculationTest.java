package strategies;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.net.SyslogAppender;
import org.junit.Test;

import model.PlagiarismResult;
import model.Submission;
/**
 * This class tests weight assigned to algorithms according to moss output
 * @author Nirupama
 *
 */
public class WeightCalculationTest {
	private static final double EPSILON=0.01;
	/**
	 * Test case for calculating weights for each of the algorithms LCS, Editdistance and ASTStrategy
	 */	
	@Test
	public void testWeights(){
		WeightCalculation c=new WeightCalculation();
		double[][] algorithmData= {{99.0,77.8,63.9},{23.0,19.6,17.54},
				{55.88,66.22,45.63},{6.08,7.0,8.0}};
		double[][] mossData= {{88.6},{21.1},{57.3},{6.78}};
		double[][] weight=c.getWeights(algorithmData, mossData);
		assertEquals(0.60,weight[0][0],EPSILON);
		assertEquals(0.26,weight[1][0],EPSILON);
		assertEquals(0.14,weight[2][0],EPSILON);

	}

	/**
	 * Test case for calculating weights for each of the algorithms LCS, Editdistance and ASTStrategy and geberating result using 
	 * weighted polynomial function
	 */	
	@Test
	public void testWeightsForLargeData(){
		WeightCalculation c=new WeightCalculation();
		List<Submission> list=new ArrayList<>();
		Submission sub1=new Submission("student1","hw1",new File("src/main/resources/moss/student11/3sum.py"));
		Submission sub2=new Submission("student1","hw1",new File("src/main/resources/moss/student12/4sum.py"));
		Submission sub3=new Submission("student1","hw1",new File("src/main/resources/moss/student13/balanced-binary-tree.py"));
		Submission sub4=new Submission("student1","hw1",new File("src/main/resources/moss/student14/asteroid-collision.py"));
		Submission sub5=new Submission("student1","hw1",new File("src/main/resources/moss/student15/average-of-levels-in-binary-tree.py"));
		Submission sub6=new Submission("student1","hw1",new File("src/main/resources/moss/student16/assign-cookies.py"));
		Submission sub7=new Submission("student2","hw1",new File("src/main/resources/moss/student21/accounts-merge.py"));
		Submission sub8=new Submission("student2","hw1",new File("src/main/resources/moss/student22/add-and-search-word-data-structure-design.py"));
		Submission sub9=new Submission("student2","hw1",new File("src/main/resources/moss/student23/add-binary.py"));
		Submission sub10=new Submission("student2","hw1",new File("src/main/resources/moss/student24/add-bold-tag-in-string.py"));
		Submission sub11=new Submission("student3","hw1",new File("src/main/resources/moss/student31/combination-sum-ii.py"));
		Submission sub12=new Submission("student3","hw1",new File("src/main/resources/moss/student32/combination-sum-iii.py"));
		Submission sub13=new Submission("student3","hw1",new File("src/main/resources/moss/student33/combination-sum-iv.py"));
		Submission sub14=new Submission("student3","hw1",new File("src/main/resources/moss/student34/compare-version-numbers.py"));
		Submission sub15=new Submission("student3","hw1",new File("src/main/resources/moss/student35/complex-number-multiplication.py"));
		list.add(sub1);
		list.add(sub2);
		list.add(sub3);
		list.add(sub4);
		list.add(sub5);
		list.add(sub6);
		list.add(sub7);
		list.add(sub8);
		list.add(sub9);
		list.add(sub10);
		list.add(sub11);
		list.add(sub12);
		list.add(sub13);
		list.add(sub14);
		list.add(sub15);
		double[][] weight=c.weightCalculation(list);
		assertEquals(0.20,weight[0][0],EPSILON);
		assertEquals(-0.09,weight[1][0],EPSILON);
		assertEquals(0.85,weight[2][0],EPSILON);
		List<PlagiarismResult> result=c.getReport(list);
		assertEquals(95.79,result.get(0).getPercentage1(),EPSILON);
		assertEquals(95.80,result.get(0).getPercentage2(),EPSILON);

	}


}
