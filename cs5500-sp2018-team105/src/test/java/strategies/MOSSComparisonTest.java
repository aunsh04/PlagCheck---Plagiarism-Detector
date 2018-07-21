package strategies;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
/**
 * This test class tests moss results for students files
 * @author Nirupama
 *
 */
public class MOSSComparisonTest {
	/**
	 * Test case for comparing homework files in course directory structure with two students
	 */
	@Test
	public void mossTest() {
		MOSSComparison moss=new MOSSComparison();
		List<Double> result=new ArrayList<>();
		result.add(45.0);
		result.add(88.0);
		assertEquals(result,moss.mossResult("src/main/resources/moss1"));

	}

}
