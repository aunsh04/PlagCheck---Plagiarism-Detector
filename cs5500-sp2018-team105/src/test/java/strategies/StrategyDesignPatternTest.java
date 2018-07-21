package strategies;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import model.PlagiarismResult;
import model.Submission;
import strategypattern.Context;
public class StrategyDesignPatternTest {
	private static double EPSILON=0.01;
	/**
	 * Test case for AST Strategy using strategy design pattern
	 */
	@Test
	public void test() {
		Context context = new Context();
		context.setStrategy(new ASTStrategy());
		List<Submission> list=new ArrayList<>();
        Submission sub1=new Submission("student-125","hw1",new File("src/main/resources/3sum.py"));
        Submission sub2=new Submission("student-126","hw1",new File("src/main/resources/3sum.py"));
        list.add(sub1);
        list.add(sub2);
		List<PlagiarismResult> result=context.executeStrategy(list);
		for(PlagiarismResult rs: result)
		{
			assertEquals(99.34, rs.getPercentage1(),EPSILON);
		}
	}

}